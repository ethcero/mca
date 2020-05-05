const tableName = process.env.ENTRIES_TABLE;
const dynamodb = require('aws-sdk/clients/dynamodb');
const docClient = process.env.AWS_DYNAMODB_LOCAL ? new dynamodb.DocumentClient({
  endpoint: process.env.AWS_DYNAMODB_LOCAL
}) : new dynamodb.DocumentClient()

exports.getByIdHandler = async (event) => {
  if (event.httpMethod !== 'GET') {
    throw new Error(`getMethod only accept GET method, you tried: ${event.httpMethod}`);
  }
  console.info('received:', event);
  const id = event.pathParameters.entryId;
  var params = {
    TableName : tableName,
    Key: { _id: id },
  };

  let response = {};
  try {
    const data = await docClient.get(params).promise();
    const item = data.Item;
    response.statusCode = 200;
    response.body = JSON.stringify(item);
  } catch (error) {
    response.statusCode = 404;
  }

  console.info(`response from: ${event.path} statusCode: ${response.statusCode} body: ${response.body}`);
  return response;
}
