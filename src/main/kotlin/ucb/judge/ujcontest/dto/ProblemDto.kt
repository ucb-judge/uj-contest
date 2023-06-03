package ucb.judge.ujcontest.dto

data class ProblemDto(
    val problemId : Long,
    val title : String,
    val problemTags: List<String>,
)
