
const db = require('./database');


exports.create = async (message) => {
  let log = {
    date: Date.now(),
    message: message
  }
   await db.getDB().collection("logs").insertOne(log)
}
