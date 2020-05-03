const lambda = require('../../../src/handlers/delete-comment.js');
const dynamodb = require('aws-sdk/clients/dynamodb');

describe('Test deleteCommentHandler', function () {
    let putSpy;

    beforeAll(() => {
        putSpy = jest.spyOn(dynamodb.DocumentClient.prototype, 'update');
    });
    afterAll(() => {
        putSpy.mockRestore();
    });

    it('should delete comment', async () => {
        const returnedItem = { _id: 'id1', name: 'name1', comments: [] };

        putSpy.mockReturnValue({
            promise: () => Promise.resolve(returnedItem)
        });

        const event = {
            httpMethod: 'DELETE',
            pathParameters: {
                entryId: "id1",
                commentId: "comment1"
            }
        };

        const result = await lambda.deleteCommentHandler(event);
        const expectedResult = {
            statusCode: 204
        };
        expect(result).toEqual(expectedResult);
    });
});
