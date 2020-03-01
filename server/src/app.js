const express = require('express')
const app = express()
const expressWs = require('express-ws')(app)
const amqp = require('./amqp-service')
const database = require('./database')
const LogCollection = require('./log-repository')

app.use(express.static('./public'))
app.use('/', express.json())

let currentTask = {};
let lastMessage;

app.post('/task', function (req, res) {
    console.log('/task endpoint executed')
    LogCollection.create('/task endpoint executed. Body: '.concat(req.body.text))
    if(req.body.text != '') {
        currentTask = {
            "id": 1,
            "text": req.body.text
        }
        amqp.publish(JSON.stringify(currentTask))
        res.json(currentTask)
        res.end()
    }else {
        res.status(400).end()
    }


})

app.get('/task/:id', function (req, res) {
    console.log('/task/:id endpoint executed')
    LogCollection.create('/task/:id endpoint executed. taskId: '.concat(req.params.id))
    if(req.params.id == currentTask.id){
        if(lastMessage) {
            res.send(lastMessage)
        } else {
            res.send("Not data yet")
        }
    }else{
        res.status(404).end()
    }
})

app.ws('/notifications', function (ws, req) {
    amqp.consume((payload) => {
        console.log("Sending to WS client: "+payload)
        lastMessage = payload
        ws.send(lastMessage)
    })
})


const MONGODB_URL = process.env.MONGODB_URL || 'mongodb://localhost:27017/server';
const RABBITMQ_URL = process.env.RABBITMQ_URL || 'amqp://guest:guest@localhost';

startup = async () => {
    database.connect(MONGODB_URL)
    amqp.connect(RABBITMQ_URL);

    app.listen(8080, function () {
    console.log('Server listening on port 8080!')
   })
}

startup()
