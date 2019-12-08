const express = require('express')
const app = express()
const expressWs = require('express-ws')(app)
const amqp = require('./amqp-service')

app.use(express.static('./public'))
app.use('/', express.json())

let currentTask;
let lastMessage;

app.post('/task', function (req, res) {
    console.log('/task endpoint executed')
    if(req.body.text != '') {
        currentTask = {
            "id": 1,
            "text": req.body.text
        }
        amqp.publish(JSON.stringify(currentTask))
        res.json({taskId: currentTask.id })
        res.end()
    }else {
        res.status(400).end()
    }
    
    
})

app.get('/task/:id', function (req, res) {   
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
        console.log("payload of WS consumer: "+payload)
        lastMessage = payload
        ws.send(lastMessage)
    })
})


app.listen(8080, function () {
 console.log('Server listening on port 8080!')
})