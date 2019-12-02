const { Router } = require('express')
const PostController = require('../controllers/post-controller')

const router = Router()

router.get('/', (req, res, next) => PostController.search(req,res, next))

router.get('/:postId', (req, res, next) => PostController.search(req,res, next))

router.post('/', (req, res, next) => PostController.create(req, res, next))

router.put('/:postId', (req, res, next) => PostController.update(req, res, next))

router.delete('/:postId', (req, res, next) => PostController.delete(req, res, next))

router.post('/:postId/comments', (req, res, next) => PostController.createComment(req, res, next))

router.delete('/:postId/comments/:commentId', (req, res, next) =>  PostController.deleteComment(req, res, next))


module.exports = router