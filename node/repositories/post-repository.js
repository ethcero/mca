const {MongoClient, ObjectId } = require('mongodb');
const url = "mongodb://localhost:27017/blog";

var postCollection

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}).then(db => {
    postCollection = db.db('blog').collection("posts")
})


exports.create = async (req) => {
   res = await postCollection.insertOne(req.body)
   return res.insertedId
}

exports.search = (req) => {
    if(req.params.postId) {
        return postCollection.findOne({"_id": new ObjectId(req.params.postId)})
    }else {
        return postCollection.find({}).toArray()
    }
   
}

exports.update = (req) => {}

exports.delete = (req) => postCollection.deleteOne({"_id": new ObjectId(req.params.postId)})

exports.createComment = (req) => {}

exports.deleteComment = (req) => {}

