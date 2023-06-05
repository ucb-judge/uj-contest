package ucb.judge.ujcontest.mapper.impl

import ucb.judge.ujcontest.dao.Problem
import ucb.judge.ujcontest.dto.ProblemDto
import ucb.judge.ujcontest.mapper.ProblemMapper

class ProblemMapperImpl : ProblemMapper{
    override fun toDto(problem: Problem): ProblemDto {
        return ProblemDto(
            problem.problemId,
            problem.title,
//            problem.problemTags!!.map { it.tag!!.name }
        )
    }
}