package ucb.judge.ujcontest.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ucb.judge.ujcontest.dao.ContestProblem

interface ContestProblemRepository : JpaRepository<ContestProblem, Long> {
    fun findAllByContestContestId(contestId: Long): List<ContestProblem>
}