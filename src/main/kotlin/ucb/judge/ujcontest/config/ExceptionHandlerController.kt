package ucb.judge.ujcontest.config

import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ucb.judge.ujcontest.dto.ResponseDto
import ucb.judge.ujcontest.exception.UjNotFoundException

@ControllerAdvice
class ExceptionHandlerController {

    companion object {
        private val logger = LoggerFactory.getLogger(ExceptionHandlerController::class.java.name)
    }

    @ExceptionHandler(UjNotFoundException::class)
    fun handleUjNotFoundException(ex: UjNotFoundException): ResponseEntity<ResponseDto<Nothing>> {
        return ResponseEntity(ResponseDto(null, ex.message!!, false), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(FeignException::class)
    fun handleFeignException(ex: FeignException): ResponseEntity<ResponseDto<Nothing>> {
        logger.error("Error message: ${ex.message}")
        val http = mapOf(
            400 to HttpStatus.BAD_REQUEST,
            401 to HttpStatus.UNAUTHORIZED,
            403 to HttpStatus.FORBIDDEN,
            404 to HttpStatus.NOT_FOUND,
            500 to HttpStatus.INTERNAL_SERVER_ERROR,
        )
        return ResponseEntity(ResponseDto(null, ex.message!!, false),http[ex.status()]!!)
    }
}