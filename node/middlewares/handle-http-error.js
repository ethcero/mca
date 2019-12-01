const HttpStatus = require('../constants/http-status')
const HttpError = require('../errors/http-error')

function handleHttpError (err, req, res, next) {
  if (err instanceof SyntaxError && err.status === 400 && 'body' in err) {
    req.err = err
    err = new HttpError(HttpStatus.BAD_REQUEST, err.message)
  } else if (!(err instanceof HttpError)) {
    req.err = err
    err = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR)
  }

  res
    .status(err.status)
    .json({
      error: err.message
    })
}

module.exports = handleHttpError
