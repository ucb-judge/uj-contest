package ucb.judge.ujcontest.dto

data class ContestScoreboardDto(
    var student: StudentDto,
    var contest: ContestDto,
    var problemsSolved: Int,
    var rank: Int
)