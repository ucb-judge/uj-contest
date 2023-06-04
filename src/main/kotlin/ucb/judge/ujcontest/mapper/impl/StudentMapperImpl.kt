package ucb.judge.ujcontest.mapper.impl

import ucb.judge.ujcontest.dao.Student
import ucb.judge.ujcontest.dto.StudentDto
import ucb.judge.ujcontest.mapper.StudentMapper

class StudentMapperImpl : StudentMapper {
    override fun toDto(student: Student) : StudentDto {
        return StudentDto(
            student.kcUuid,
            student.studentId
        )
    }
}