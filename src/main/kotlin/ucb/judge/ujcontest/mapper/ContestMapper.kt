package ucb.judge.ujcontest.mapper

import org.mapstruct.Mapper
import ucb.judge.ujcontest.dao.Contest
import ucb.judge.ujcontest.dto.ContestDto

@Mapper
interface ContestMapper  {
    fun toDto(contest: Contest) : ContestDto;
    fun toEntity(contestDto: ContestDto) : Contest;
}