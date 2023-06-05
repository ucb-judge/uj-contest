package ucb.judge.ujcontest.dto

data class ContestScoreboardDto(
    var student: UserDetailsDto,
    var problemsSolved: Int,
)