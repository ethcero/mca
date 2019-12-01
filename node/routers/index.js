const { json, Router } = require('express')
const HttpError = require('../errors/http-error')

const router = Router()

router.use('/', json())

router.use('/', require('./post-router'))

router.use('/', (req, res, next) => {
    next(new HttpError(404))
})

router.use('/', require('../middlewares/handle-http-error'))

module.exports = router
