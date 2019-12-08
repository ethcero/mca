const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');

var packageDefinition = protoLoader.loadSync(__dirname + '/../toUpperCaseService.proto',
    {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
    });

var toUpperCaseServiceProto = grpc.loadPackageDefinition(packageDefinition);

module.exports = toUpperCaseServiceProto.es.ethcero.mca.grpc.ToUpperCaseService;