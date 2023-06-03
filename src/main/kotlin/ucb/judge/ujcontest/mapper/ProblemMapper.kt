package ucb.judge.ujcontest.mapper

import org.mapstruct.Mapper
import ucb.judge.ujcontest.dao.Problem
import ucb.judge.ujcontest.dto.ProblemDto

@Mapper
interface ProblemMapper {
    fun toDto(problem: Problem) : ProblemDto;
}