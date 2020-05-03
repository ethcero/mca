const dynamodb = require('aws-sdk/clients/dynamodb');

const docClient = process.env.AWS_DYNAMODB_LOCAL ? new dynamodb.DocumentClient({
    endpoint: process.env.AWS_DYNAMODB_LOCAL
}) : new dynamodb.DocumentClient()


const tableName = process.env.ENTRIES_TABLE;

exports.postEntryHandler = async (event) => {
    if (event.httpMethod !== 'POST') {
        throw new Error(`postEntry only accepts POST method, you tried: ${event.httpMethod} method.`);
    }
    console.info('received:', event);

    const body = JSON.parse(event.body)
    var params = {
        TableName: tableName,
        Item: body
    };

    const result = await docClient.put(params).promise();

    const response = {
        statusCode: 200,
        body: JSON.stringify(body)
    };

    // All log statements are written to CloudWatch
    console.info(`response from: ${event.path} statusCode: ${response.statusCode} body: ${response.body}`);
    return response;
}
