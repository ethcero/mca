const dynamodb = require('aws-sdk/clients/dynamodb');
const docClient = process.env.AWS_DYNAMODB_LOCAL ? new dynamodb.DocumentClient({
    endpoint: process.env.AWS_DYNAMODB_LOCAL
}) : new dynamodb.DocumentClient()

const tableName = process.env.ENTRIES_TABLE;

exports.putEntryHandler = async (event) => {
    if (event.httpMethod !== 'PUT') {
        throw new Error(`putEntry only accepts PUT method, you tried: ${event.httpMethod} method.`);
    }
    console.info('received:', event);

    const body = JSON.parse(event.body)
    const id = event.pathParameters.id;
    var params = {
        TableName: tableName,
        Key: {_id: id},
        Item: body,
        ConditionExpression: '#id = :id',
        ExpressionAttributeNames: {
            "#id":"_id"
        },
        ExpressionAttributeValues: {
            ':id': id
        },
        ReturnValues: "ALL_NEW"
    };

    let response = {};
    try {
        const result = await docClient.update(params).promise();
        response.statusCode = 200;
        response.body = JSON.stringify(result.Attributes)
    } catch (error) {
        response.statusCode = 404;
    }

    // All log statements are written to CloudWatch
    console.info(`response from: ${event.path} statusCode: ${response.statusCode} body: ${response.body}`);
    return response;
}
