const { Router } = require('express')
const PostController = require('../controllers/post-controller')

const router = Router()

router.get('/', function (req, res) {
    PostController.search(req,res)
})

router.get('/:postId', function (req, res) {
    PostController.search(req,res)
})

router.post('/', function (req, res) {    
    PostController.create(req, res)    
})

router.put('/:postId', function (req, res) {
    PostController.update(req, res)
})

router.delete('/:postId', function (req, res) {
    PostController.delete(req, res)
})

router.post('/:postId/comments', function (req, res) {
    PostController.createComment(req, res)
})

router.delete('/:postId/comments/:commentId', function (req, res) {
    PostController.deleteComment(req, res)
})


module.exports = router