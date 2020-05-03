const dynamodb = require('aws-sdk/clients/dynamodb');
const docClient = process.env.AWS_DYNAMODB_LOCAL ? new dynamodb.DocumentClient({
    endpoint: process.env.AWS_DYNAMODB_LOCAL
}) : new dynamodb.DocumentClient()

const tableName = process.env.ENTRIES_TABLE;

exports.deleteCommentHandler = async (event) => {
    if (event.httpMethod !== 'DELETE') {
        throw new Error(`deleteComment only accepts DELETE method, you tried: ${event.httpMethod} method.`);
    }
    console.info('received:', event);

    const id = event.pathParameters.entryId;
    const commentId = event.pathParameters.commentId;

    var searchParams = {
        TableName : tableName,
        Key: { _id: id }
    };
    const data = await docClient.get(searchParams).promise();
    const entry = data.Item;

    const comments = entry.comments.filter(e => e._id !== commentId);

    var params = {
        TableName : tableName,
        Key: { _id: id },
        UpdateExpression: "SET comments = :comments",
        ExpressionAttributeValues: {
            ":comments": comments
        },
    };

    const result = await docClient.update(params).promise();

    const response = {
        statusCode: 204
    };

    // All log statements are written to CloudWatch
    console.info(`response from: ${event.path} statusCode: ${response.statusCode} body: ${response.body}`);
    return response;
}
