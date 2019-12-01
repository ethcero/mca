/**
 * Module.
 */

const HttpStatus = {
    // 2xx Successful
    OK: 200,
    CREATED: 201,
    NO_CONTENT: 204,
    // 4xx Client Error
    BAD_REQUEST: 400,
    UNAUTHORIZED: 401,
    FORBIDDEN: 403,
    NOT_FOUND: 404,
    METHOD_NOT_ALLOWED: 405,
    NOT_ACCEPTABLE: 406,
    CONFLICT: 409,
    UNSUPPORTED_MEDIA_TYPE: 415,
    // 5xx Server Error
    INTERNAL_SERVER_ERROR: 500,
    BAD_GATEWAY: 502,
    SERVICE_UNAVAILABLE: 503
  }
  
  /**
   * Module exports.
   */
  
  module.exports = HttpStatus
  