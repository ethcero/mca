const lambda = require('../../../src/handlers/get-all-entries.js');
const dynamodb = require('aws-sdk/clients/dynamodb');
describe('Test getAllEntriesHandler', () => {
    let scanSpy;

    beforeAll(() => {
        // Mock dynamodb get and put methods
        // https://jestjs.io/docs/en/jest-object.html#jestspyonobject-methodname
        scanSpy = jest.spyOn(dynamodb.DocumentClient.prototype, 'scan');
    });
    afterAll(() => {
        scanSpy.mockRestore();
    });

    it('should return ids', async () => {
        const items = [{ _id: 'id1' }, { _id: 'id2' }];
        scanSpy.mockReturnValue({
            promise: () => Promise.resolve({ Items: items })
        });

        const event = {
            httpMethod: 'GET'
        }
        const result = await lambda.getAllEntriesHandler(event);

        const expectedResult = {
            statusCode: 200,
            body: JSON.stringify(items)
        };

        expect(result).toEqual(expectedResult);
    });
});
