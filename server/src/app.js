const express = require('express')
const app = express()
const expressWs = require('express-ws')(app)
const amqp = require('./amqp-service')

app.use(express.static('./public'))
app.use('/', express.json())

let progress = 0

app.post('/task', function (req, res) {
    console.log('/task endpoint executed')
    if(req.body.text != '') {
        amqp.publish(req.body.text)
    }
    res.json({message:"Your text is: " + req.body.text})
    res.end()
})

app.get('/task/:id', function (req, res) {   
    res.send(progress)
})

app.ws('/notifications', function (ws, req) {
    amqp.consume((payload) => {
        console.log("payload of WS consumer: "+payload)
        progress = payload
        ws.send(progress)
    })
})


app.listen(8080, function () {
 console.log('Server listening on port 8080!')
})