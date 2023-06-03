package ucb.judge.ujcontest.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ucb.judge.ujcontest.dao.Professor

interface ProfessorRepository  : JpaRepository<Professor, Long>{
    fun findByKcUuid(kcUuid: String): Professor?
    fun findByProfessorIdAndStatusIsTrue(professorId: Long): Professor?
}
