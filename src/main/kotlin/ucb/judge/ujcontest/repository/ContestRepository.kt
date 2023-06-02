package ucb.judge.ujcontest.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ucb.judge.ujsubmissions.dao.Contest

@Repository
interface ContestRepository : JpaRepository<Contest, Long>{
    fun findByContestId(contestIsd: Long): Contest?
    fun findByContestName(contestName: String): Contest?
}