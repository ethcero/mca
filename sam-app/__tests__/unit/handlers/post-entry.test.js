const lambda = require('../../../src/handlers/post-entry.js');
const dynamodb = require('aws-sdk/clients/dynamodb');

describe('Test postEntryHandler', function () {
    let putSpy;

    beforeAll(() => {
        putSpy = jest.spyOn(dynamodb.DocumentClient.prototype, 'put');
    });
    afterAll(() => {
        putSpy.mockRestore();
    });

    it('should add new entry', async () => {
        const returnedItem = { _id: 'id1', name: 'name1' };

        putSpy.mockReturnValue({
            promise: () => Promise.resolve(returnedItem)
        });

        const event = {
            httpMethod: 'POST',
            body: '{"_id": "id1","name": "name1"}'
        };

        const result = await lambda.postEntryHandler(event);
        const expectedResult = {
            statusCode: 200,
            body: JSON.stringify(returnedItem)
        };
        expect(result).toEqual(expectedResult);
    });
});
