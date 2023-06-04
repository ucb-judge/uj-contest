package ucb.judge.ujcontest.mapper.impl

import ucb.judge.ujcontest.dao.ContestScoreboard
import ucb.judge.ujcontest.dto.ContestScoreboardDto
import ucb.judge.ujcontest.mapper.ContestMapper
import ucb.judge.ujcontest.mapper.ContestScoreboardMapper
import ucb.judge.ujcontest.mapper.StudentMapper

class ContestScoreboardMapperImpl : ContestScoreboardMapper {
    override fun toDto(contestScoreboard: ContestScoreboard) : ContestScoreboardDto {
        val studentMapper : StudentMapper = StudentMapperImpl()
        val contestMapper : ContestMapper = ContestMapperImpl()
        return ContestScoreboardDto(
            student = studentMapper.toDto(contestScoreboard.student!!),
            contest = contestMapper.toDto(contestScoreboard.contest!!),
            problemsSolved = contestScoreboard.problemsSolved,
            rank = contestScoreboard.rank
        )
    }
}