package ucb.judge.ujcontest.mapper

import org.mapstruct.Mapper
import ucb.judge.ujcontest.dao.Student
import ucb.judge.ujcontest.dto.StudentDto

@Mapper
interface StudentMapper {
    fun toDto(student: Student): StudentDto
}