package ucb.judge.ujcontest.mapper.impl

import ucb.judge.ujcontest.dao.Contest
import ucb.judge.ujcontest.dto.ContestDto
import ucb.judge.ujcontest.mapper.ContestMapper
import ucb.judge.ujcontest.mapper.ProfessorMapper
import ucb.judge.ujcontest.mapper.SubjectMapper

class ContestMapperImpl : ContestMapper {
    val professorMapper : ProfessorMapper = ProfessorMapperImpl()
    val subjectMapper : SubjectMapper = SubjectMapperImpl()

    override fun toDto(contest: Contest): ContestDto {
        return ContestDto(
            contestId = contest.contestId,
            name = contest.name,
            description = contest.description,
            startDate = contest.startingDate,
            endDate = contest.endingDate,
            professor =  professorMapper.toDto(contest.professor),
            subject = if (contest.subject == null) null else subjectMapper.toDto(contest.subject!!),
            // TODO: check todo in ContestDto.kt
            isPublic = contest.isPublic
        );
    }

    override fun toEntity(contestDto: ContestDto) : Contest {
        val contest = Contest()
        contest.contestId = contestDto.contestId
        contest.name = contestDto.name
        contest.description = contestDto.description
        contest.startingDate = contestDto.startDate
        contest.endingDate = contestDto.endDate
        contest.professor = professorMapper.toEntity(contestDto.professor!!)
        contest.subject = if (contestDto.subject == null) null else subjectMapper.toEntity(contestDto.subject!!)
        contest.isPublic = contestDto.isPublic
        return contest
    }
}