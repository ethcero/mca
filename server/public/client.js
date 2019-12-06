
let socket = new WebSocket("ws://"+window.location.host+"/notifications")
var xhttp = new XMLHttpRequest()

xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        setProgress(this.responseText)
    }
  }


socket.onopen = function (e) {
    console.log("WebSocket connection established")
}

socket.onmessage = function (event) {
    console.log(`[message] Data received from server: ${event.data}`)
    setProgress(event.data) 
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
    xhttp.open("GET", "http://"+window.location.host+"/task/fakeid", true)
    xhttp.send();
}

function setProgress(value) {
    document.getElementById("progress").innerHTML = `Progress: ${value}%` 
}