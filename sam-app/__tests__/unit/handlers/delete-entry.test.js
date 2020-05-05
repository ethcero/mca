const lambda = require('../../../src/handlers/delete-entry.js');
const dynamodb = require('aws-sdk/clients/dynamodb');

describe('Test deleteEntryHandler', function () {
    let putSpy;

    beforeAll(() => {
        putSpy = jest.spyOn(dynamodb.DocumentClient.prototype, 'delete');
    });
    afterAll(() => {
        putSpy.mockRestore();
    });

    it('should delete entry', async () => {
        const returnedItem = 'id1';

        putSpy.mockReturnValue({
            promise: () => Promise.resolve(returnedItem)
        });

        const event = {
            httpMethod: 'DELETE',
            pathParameters: {
                entryId: "id1"
            }
        };

        const result = await lambda.deleteEntryHandler(event);
        const expectedResult = {
            statusCode: 204,
            body: returnedItem
        };
        expect(result).toEqual(expectedResult);
    });
});
