package ucb.judge.ujcontest.mapper

import org.mapstruct.Mapper
import ucb.judge.ujcontest.dao.Subject
import ucb.judge.ujcontest.dto.SubjectDto

@Mapper
interface SubjectMapper {
    fun toDto (subject: Subject) : SubjectDto
    fun toEntity (subjectDto: SubjectDto) : Subject
}