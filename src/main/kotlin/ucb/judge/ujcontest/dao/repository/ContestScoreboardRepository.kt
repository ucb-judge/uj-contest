package ucb.judge.ujcontest.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ucb.judge.ujcontest.dao.ContestScoreboard

interface ContestScoreboardRepository : JpaRepository<ContestScoreboard, Long> {
    fun findByContestContestId(contestId: Long): List<ContestScoreboard>
}