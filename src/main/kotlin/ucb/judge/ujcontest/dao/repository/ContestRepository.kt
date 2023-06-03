package ucb.judge.ujcontest.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ucb.judge.ujcontest.dao.Contest

interface ContestRepository  : JpaRepository<Contest, Long>{
    fun findAllByStatusIsTrue(): List<Contest>
    fun findByContestIdAndStatusIsTrue(contestId: Long): Contest?
}