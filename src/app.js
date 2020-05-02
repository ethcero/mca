var express = require('express');
var EntryRepository = require('./repository.js').EntryRepository

var myRepository = new EntryRepository();

app = express();
app.use(express.json()); // support json encoded bodies

app.get("/", async (req, res) => {
    var response = await myRepository.getAllEntries();
    res.json(response);
});

app.get("/:entryId", async (req, res) => {
    var response = await myRepository.getEntry(req.params.entryId);
    if (response) {
        res.json(response);
    } else {
        res.sendStatus(404);
    }
});

app.post("/", async (req, res) => {
    var entry = req.body;
    var response = await myRepository.saveEntry(entry);
    res.json(response.ops[0]);
});

app.delete("/:entryId", async (req, res) => {
    var response = await myRepository.removeEntry(req.params.entryId);
    if (response.deletedCount) {
        res.sendStatus(204);
    } else {
        res.sendStatus(404);
    }
});

app.put("/:entryId", async (req, res) => {
    var entry = req.body;
    entry._id = req.params.entryId;
    var response = await myRepository.updateEntry(entry);
    if (response.matchedCount) {
        if (response.result.nModified) {
            res.json(entry); // Successfully updated
        } else {
            res.sendStatus(304); // Not modified
        }
    } else {
        res.sendStatus(404); // Not found
    }
});

app.post("/:entryId/comments/", async (req, res) => {
    var comment = req.body;
    var entryId = req.params.entryId;
    var response = await myRepository.saveComment(entryId, comment);
    if (response.value) {
        res.json(response.value);
    } else {
        res.sendStatus(404);
    }
});

app.delete("/:entryId/comments/:commentId", async (req, res) => {
    var response = await myRepository.removeComment(req.params.entryId, req.params.commentId);
    if (response.result.nModified) {
        res.sendStatus(204);
    } else {
        res.sendStatus(404);
    }
});

(async () => {
    await myRepository.openConnection();
    await app.listen(8080);
    console.log('App listening on port 8080');
})();