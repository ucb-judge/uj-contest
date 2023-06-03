package ucb.judge.ujcontest.mapper.impl

import ucb.judge.ujcontest.dao.Subject
import ucb.judge.ujcontest.dto.SubjectDto
import ucb.judge.ujcontest.mapper.SubjectMapper
import java.sql.Date

class SubjectMapperImpl : SubjectMapper {
    override fun toDto(subject: Subject): SubjectDto {
        return SubjectDto(
            subjectId = subject.subjectId,
            name = subject.name,
            code = subject.code,
            dateFrom = subject.dateFrom,
            dateTo = subject.dateTo
        )
    }

    override fun toEntity(subjectDto: SubjectDto): Subject {
        val subject = Subject()
        subject.subjectId = subjectDto.subjectId
        subject.name = subjectDto.name
        subject.code = subjectDto.code
        subject.dateFrom = subjectDto.dateFrom as Date
        subject.dateTo = subjectDto.dateTo as Date

        return subject
    }
}