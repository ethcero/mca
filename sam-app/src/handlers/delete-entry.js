const dynamodb = require('aws-sdk/clients/dynamodb');
const docClient = process.env.AWS_DYNAMODB_LOCAL ? new dynamodb.DocumentClient({
    endpoint: process.env.AWS_DYNAMODB_LOCAL
}) : new dynamodb.DocumentClient()

const tableName = process.env.ENTRIES_TABLE;

exports.deleteEntryHandler = async (event) => {
    if (event.httpMethod !== 'DELETE') {
        throw new Error(`deleteEntry only accepts DELETE method, you tried: ${event.httpMethod} method.`);
    }
    console.info('received:', event);

    const id = event.pathParameters.entryId;
    var params = {
        TableName: tableName,
        Key: {_id: id}
    };

    const result = await docClient.delete(params).promise();

    const response = {
        statusCode: 204,
        body: id
    };

    // All log statements are written to CloudWatch
    console.info(`response from: ${event.path} statusCode: ${response.statusCode} body: ${response.body}`);
    return response;
}
