package ucb.judge.ujcontest.mapper.impl

import ucb.judge.ujcontest.dao.Professor
import ucb.judge.ujcontest.dto.ProfessorDto
import ucb.judge.ujcontest.mapper.ProfessorMapper

class ProfessorMapperImpl : ProfessorMapper {
    override fun toDto(professor: Professor): ProfessorDto {
        return ProfessorDto(
            professorId = professor.professorId,
            kcUuid = professor.kcUuid,
        );
    }

    override fun toEntity(professorDto: ProfessorDto) : Professor {
        val professor = Professor()
        professor.professorId = professorDto.professorId
        professor.kcUuid = professorDto.kcUuid
        return professor
    }
}