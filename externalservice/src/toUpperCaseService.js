
function toUpperCase(call, callback){

    console.log('Request received: '+JSON.stringify(call));

    var { text } = call.request;

    callback(null, { result: text.toUpperCase() });
}

exports.toUpperCase = toUpperCase;