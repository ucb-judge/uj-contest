package ucb.judge.ujcontest.mapper

import org.mapstruct.Mapper
import ucb.judge.ujcontest.dao.ContestScoreboard
import ucb.judge.ujcontest.dto.ContestScoreboardDto

@Mapper
interface ContestScoreboardMapper {
    fun toDto(contestScoreboard: ContestScoreboard) : ContestScoreboardDto
}