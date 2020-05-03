const dynamodb = require('aws-sdk/clients/dynamodb');
const docClient = process.env.AWS_DYNAMODB_LOCAL ? new dynamodb.DocumentClient({
    endpoint: process.env.AWS_DYNAMODB_LOCAL
}) : new dynamodb.DocumentClient()

const tableName = process.env.ENTRIES_TABLE;

exports.postCommentHandler = async (event) => {
    if (event.httpMethod !== 'POST') {
        throw new Error(`postComment only accepts POST method, you tried: ${event.httpMethod} method.`);
    }
    console.info('received:', event);

    const body = JSON.parse(event.body)
    const id = event.pathParameters.entryId;
    var params = {
        TableName: tableName,
        Key: {_id: id},
        UpdateExpression: "SET comments = list_append(if_not_exists(comments, :empty_list), :comment)",
        ExpressionAttributeValues: {
            ":comment": [body],
            ":empty_list": []
        },
    };
    const result = await docClient.update(params).promise();

    const response = {
        statusCode: 200
    };

    // All log statements are written to CloudWatch
    console.info(`response from: ${event.path} statusCode: ${response.statusCode} body: ${response.body}`);
    return response;
}
