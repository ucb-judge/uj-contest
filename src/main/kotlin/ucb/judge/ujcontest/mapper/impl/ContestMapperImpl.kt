package ucb.judge.ujcontest.mapper.impl

import ucb.judge.ujcontest.dao.Contest
import ucb.judge.ujcontest.dto.ContestDto
import ucb.judge.ujcontest.mapper.ContestMapper

class ContestMapperImpl : ContestMapper {
    override fun toDto(contest: Contest): ContestDto {
        return ContestDto(
            contestId = contest.contestId,
            name = contest.name,
            description = contest.description,
            startDate = contest.startingDate,
            endDate = contest.endingDate,
            professor = contest.professor,
            subject = contest.subject,
            // TODO: check todo in ContestDto.kt
            isPublic = contest.isPublic
        );
    }
}