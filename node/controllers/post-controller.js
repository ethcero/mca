const PostRepository = require('../repositories/post-repository')
const HttpError = require('../errors/http-error')
const HttpStatus = require('../constants/http-status')

exports.create = (req,res) => PostRepository.create(req).then( ret => res.send(doc))

exports.search = (req, res, next) => PostRepository.search(req)
    .then(entry => {
        if (!entry) {
            throw new HttpError(HttpStatus.NOT_FOUND)
        }
        res.send(entry)
    })
    .catch(next)


exports.update = (req, res, next) => PostRepository.update(req)
    .then(ret => {
        if (ret.modifiedCount == 0) {
            throw new HttpError(HttpStatus.NOT_FOUND)
        }
        res.send(ret.ops)
    })
    .catch(next)

exports.delete = (req, res, next) => PostRepository.delete(req)
    .then(ret => {
        if (ret.deletedCount == 0) {
            throw new HttpError(HttpStatus.NOT_FOUND)
        }
        res.status(204).end()
    })
    .catch(next)

exports.createComment = (req, res, next) => PostRepository.createComment(req)
    .then(ret => {
        if (ret.modifiedCount == 0) {
            throw new HttpError(HttpStatus.NOT_FOUND)
        }
        res.send(ret.ops)
    })
    .catch(next)

exports.deleteComment = (req, res, next) => PostRepository.deleteComment(req)
    .then(ret => {
        if (ret.deletedCount == 0) {
            throw new HttpError(HttpStatus.NOT_FOUND)
        }
        res.status(204).end()
    })
    .catch(next)
