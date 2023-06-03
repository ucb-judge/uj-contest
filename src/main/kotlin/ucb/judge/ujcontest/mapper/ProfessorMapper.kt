package ucb.judge.ujcontest.mapper

import org.mapstruct.Mapper
import ucb.judge.ujcontest.dao.Professor
import ucb.judge.ujcontest.dto.ProfessorDto

@Mapper
interface ProfessorMapper {
    fun toDto(professor: Professor) : ProfessorDto;
    fun toEntity(professorDto: ProfessorDto): Professor
}