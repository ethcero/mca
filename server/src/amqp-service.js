const amqp = require('amqplib/callback_api');

const CONN_URL = 'amqp://guest:guest@localhost';

let ch = null;
var taskQueue = 'newTasks';
var progressQueue = 'tasksProgress';

process.on('exit', (code) => {
    ch.close();
    console.log(`Closing rabbitmq channel`);
});


amqp.connect(CONN_URL, async function (err, conn) {
    ch = await conn.createChannel();    
    ch.assertQueue(taskQueue, {
        durable: true
      });
    ch.assertQueue(progressQueue, {
    durable: true
    });
});

exports.publish = (data) => {
    console.log('Publishing to '+ taskQueue + ': ' + data)
    ch.sendToQueue(taskQueue, Buffer.from(data));
}

exports.consume = (callback) => {
    ch.consume(progressQueue, (msg) => {
        console.log('Consuming from ' + progressQueue + ': ' + msg.content.toString('utf8'))
        callback(msg.content.toString('utf8'))
    }, { noAck: true });
}
