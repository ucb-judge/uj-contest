package ucb.judge.ujcontest.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ucb.judge.ujcontest.dao.Contest
import ucb.judge.ujcontest.dao.ContestProblem
import ucb.judge.ujcontest.dao.Problem

interface ContestProblemRepository : JpaRepository<ContestProblem, Long> {
    fun findAllByContestContestId(contestId: Long): List<ContestProblem>

    fun findByContestContestIdAndProblemProblemId(contestId: Long, problemId: Long): ContestProblem?
}