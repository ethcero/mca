const grpc = require('grpc');
const ToUpperCaseService = require('./interface');
const ToUpperCaseImpl = require('./toUpperCaseService');

const server = new grpc.Server();

server.addService(ToUpperCaseService.service, ToUpperCaseImpl);

server.bind('127.0.0.1:8082', grpc.ServerCredentials.createInsecure());

console.log('gRPC server running at http://127.0.0.1:8082');

server.start();