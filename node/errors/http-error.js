const { STATUS_CODES } = require('http')

class HttpError extends Error {
  constructor (status, message = STATUS_CODES[status]) {
    super()
    this.name = this.constructor.name
    Error.captureStackTrace(this, this.constructor)

    this.status = status
    this.message = message
  }
}

module.exports = HttpError
