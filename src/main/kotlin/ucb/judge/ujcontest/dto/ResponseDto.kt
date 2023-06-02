package ucb.judge.ujcontest.dto

data class ResponseDto <T>(
    val data: T?,
    val message: String?,
    val successful: Boolean = false
){
    companion object {
        fun <T> success(data: T, message: String?): ResponseDto<T> {
            return ResponseDto(data, message, true)
        }
        fun <T> success(data: T): ResponseDto<T> {
            return ResponseDto(data, message = null, true)
        }

        fun <T> error(message: String): ResponseDto<T> {
            return ResponseDto(null, message, false)
        }
    }
}