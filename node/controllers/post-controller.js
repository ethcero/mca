const PostRepository = require('../repositories/post-repository')
const HttpError = require('../errors/http-error')

exports.create = (req,res) => PostRepository.create(req).then( ret => res.send(doc))

exports.search = (req, res) => PostRepository.search(req)
    .then(entry => {
        if (!entry) {
            throw new HttpError(404)
        }    
        res.send(entry)}
    )


exports.update = (req, res) => PostRepository.update(req)
.then(ret => {
    if (ret.modifiedCount == 0) {
        throw new HttpError(404)
    }    
    res.send(ret.ops)}
)

exports.delete = (req, res) => PostRepository.delete(req)
    .then(ret => {
        if (ret.deletedCount == 0) {
            throw new HttpError(404)
        }    
        res.status(204).end()}
    )
