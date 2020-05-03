const lambda = require('../../../src/handlers/put-entry.js');
const dynamodb = require('aws-sdk/clients/dynamodb');

describe('Test putEntryHandler', function () {
    let putSpy;

    beforeAll(() => {
        putSpy = jest.spyOn(dynamodb.DocumentClient.prototype, 'update');
    });
    afterAll(() => {
        putSpy.mockRestore();
    });

    it('should change name to the entry', async () => {
        const returnedItem = { _id: 'id1', name: 'name1' };

        putSpy.mockReturnValue({
            promise: () => Promise.resolve(returnedItem)
        });

        const event = {
            httpMethod: 'PUT',
            pathParameters: {
                id: 'id1'
            },
            body: '{"_id": "id1","name": "name1"}'
        };

        const result = await lambda.putEntryHandler(event);
        const expectedResult = {
            statusCode: 200,
            body: JSON.stringify(returnedItem)
        };
        expect(result).toEqual(expectedResult);
    });
});
