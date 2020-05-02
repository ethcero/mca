var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

var ObjectId = require('mongodb').ObjectId;

class EntryRepository {

    constructor() {
        this.entryDB = undefined;
        this.entryCollection = undefined;
        this.projection = {
            projection: {
                name: 1,
                nickname: 1,
                title: 1,
                content: 1,
                comments: 1
            }
        };
    }

    async openConnection() {
        var self = this;
        try {
            var db = await MongoClient.connect(url, {
                useUnifiedTopology: true,
                useNewUrlParser: true
            });
            this.entryDB = db.db("entryDB");
            this.entryCollection = self.entryDB.collection("entryCollection");
            console.log('Database and collection initialized');
        } catch (err) {
            console.error('Error initializing database', error);
            throw err;
        }
    }

    getAllEntries() {
        return this.entryCollection.find({}, this.projection).toArray();
    }

    getEntry(id) {
        return this.entryCollection.findOne({
            _id: new ObjectId(id)
        }, this.projection);
    }

    saveEntry(entry) {
        return this.entryCollection.insertOne(entry);
    }

    removeEntry(id) {
        return this.entryCollection.deleteOne({
            _id: new ObjectId(id)
        });
    }

    updateEntry(entry) {
        return this.entryCollection.updateOne({
            _id: new ObjectId(entry._id)
        }, {
            $set: {
                name: entry.name,
                nickname: entry.nickname,
                title: entry.title,
                content: entry.content,
                comments: entry.comments
            }
        });
    }

    async saveComment(entryId, comment) {
        comment._id = new ObjectId();
        return this.entryCollection.findOneAndUpdate({
            _id: new ObjectId(entryId),
        }, {
            $push: {
                comments: comment
            }
        }, {
            returnOriginal: false
        });
    }

    removeComment(entryId, commentId) {
        return this.entryCollection.updateOne({
            _id: new ObjectId(entryId)
        }, {
            $pull: {
                comments: {
                    _id: new ObjectId(commentId)
                }

            }
        });
    }

}

module.exports.EntryRepository = EntryRepository