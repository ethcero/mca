
const db = require('./database');


exports.create = async (message) => {
  let log = {
    date: Date.now(),
    message: message
  }
   await db.getDB().collection("logs").insertOne(log)
}

exports.search = async (req,res) => {
    let logs = await db.getDB().collection("logs").find({}).toArray()
    res.send(logs)
}
