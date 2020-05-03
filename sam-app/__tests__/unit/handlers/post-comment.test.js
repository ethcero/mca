const lambda = require('../../../src/handlers/post-comment.js');
const dynamodb = require('aws-sdk/clients/dynamodb');

describe('Test postCommentHandler', function () {
    let putSpy;

    beforeAll(() => {
        putSpy = jest.spyOn(dynamodb.DocumentClient.prototype, 'update');
        getSpy = jest.spyOn(dynamodb.DocumentClient.prototype, 'get');
    });
    afterAll(() => {
        putSpy.mockRestore();
        getSpy.mockRestore();
    });

    it('should add new comment', async () => {
        const returnedItem = { _id: 'id1', name: 'name1', comments: [{
            "nickname": "Random Internet Guy",
            "comment": "Here is Johnny!",
            "creationDate": "2019-11-26 10:15:55"
        }] };

        putSpy.mockReturnValue({
            promise: () => Promise.resolve(returnedItem)
        });

        const entry = {
            _id: 'id1', name: 'name1', comments: []
        }
        getSpy.mockReturnValue({
            promise: () => Promise.resolve(entry)
        })

        const event = {
            httpMethod: 'POST',
            pathParameters: {
                entryId: 'id1'
            },
            body: '{"nickname": "Random Internet Guy","comment": "Here is Johnny!","creationDate": "2019-11-26 10:15:55"}'
        };

        const result = await lambda.postCommentHandler(event);
        const expectedResult = {
            statusCode: 200
        };
        expect(result).toEqual(expectedResult);
    });
});
