const amqp = require('amqplib/callback_api');

let ch = null;
var taskQueue = 'newTasks';
var progressQueue = 'tasksProgress';

const connectionOptions = {
    reconnectInterval: 1000,
    reconnectTries: 10
  }

process.on('exit', (code) => {
    if(ch) {
        ch.close();
        console.log(`Closing rabbitmq channel`);
    }
});


async function tryConnect(url, attempts) {
    try {
        amqp.connect(url, async function (err, conn) {
            ch = await conn.createChannel()
            ch.assertQueue(taskQueue, {
                durable: true
              })
            ch.assertQueue(progressQueue, {
            durable: true
            })
        })
        console.log('info', 'Connected to RabbitMQ')
    } catch (error) {
      if(attempts) {
        console.log('warn', `Failed to connect to RabbitMQ (${attempts} remaining attempts)`)
        await new Promise(resolve => setTimeout(tryConnect,connectionOptions.reconnectInterval, url, --connectionOptions.reconnectTries))
      }else {
        console.log('error', 'Failed to connect to RabbitMQ', { error })
        throw Error('Failed to connect to RabbitMQ')
      }
    }
  }

exports.connect = async (url) => {
    await tryConnect(url,connectionOptions.reconnectTries)
}

exports.publish = (data) => {
    console.log('Publishing to '+ taskQueue + ': ' + data)
    ch.sendToQueue(taskQueue, Buffer.from(data))
}

exports.consume = (callback) => {
    ch.consume(progressQueue, (msg) => {
        console.log('Consuming from ' + progressQueue + ': ' + msg.content.toString('utf8'))
        callback(msg.content.toString('utf8'))
    }, { noAck: true })
}
