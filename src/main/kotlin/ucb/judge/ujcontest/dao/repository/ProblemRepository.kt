package ucb.judge.ujcontest.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ucb.judge.ujcontest.dao.Problem

interface ProblemRepository : JpaRepository<Problem, Long> {
}