const {MongoClient, ObjectId } = require('mongodb');
const url = "mongodb://localhost:27017/server";

var logCollection

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}).then(db => {
    logCollection = db.db('server').collection("logs")
})


exports.create = async (message) => {
  let log = {
    date: Date.now(),
    message: message
  }
   await logCollection.insertOne(log)
}
