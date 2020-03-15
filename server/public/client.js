
let socket = new WebSocket("ws://"+window.location.host+"/notifications")
let xhttp = new XMLHttpRequest()

let task

xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        task = JSON.parse(this.responseText)
        processData( task)
    }
  }


socket.onopen = function (e) {
    console.log("WebSocket connection established")
}

socket.onmessage = function (event) {
    console.log(`[message] Data received from server: ${event.data}`)
    processData(JSON.parse(event.data))
}

socket.onclose = function (event) {
    if (event.wasClean) {
        console.log(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`)
    } else {
        console.log('[close] Connection died')
    }
}

socket.onerror = function (error) {
    console.log(`[error] ${error.message}`)
}

function sendMessage() {
    xhttp.open("POST", "http://"+window.location.host+"/task", true)
    xhttp.setRequestHeader("Content-type", "application/json")
    xhttp.send("{\"text\": \""+ document.getElementById("text").value + "\"}")
    console.log("REST message sent")
}

function getProgress() {
    xhttp.open("GET", "http://"+window.location.host+"/task/"+task.id, true)
    xhttp.send();
}

function processData(value) {
    let text = ''
    if(value.progress < 100) {
        text = `id: ${value.id}\nProgress: ${value.progress}%`
    } else if(value.result) {
        text = `id: ${value.id}\nResult: ${value.result}`
    }
    document.getElementById("progress").innerHTML = text
}
