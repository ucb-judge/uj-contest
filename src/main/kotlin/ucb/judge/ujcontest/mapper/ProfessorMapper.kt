package ucb.judge.ujcontest.mapper

import ucb.judge.ujcontest.dao.Professor
import ucb.judge.ujcontest.dto.ProfessorDto

interface ProfessorMapper {
    fun toDto(professor: Professor) : ProfessorDto;
}