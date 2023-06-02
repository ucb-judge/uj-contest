package ucb.judge.ujcontest.bl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ucb.judge.ujcontest.dao.Contest
import ucb.judge.ujcontest.repository.ContestRepository

@Service
class ContestBl @Autowired constructor(
    val contestRepository: ContestRepository
){
    fun getContests(): MutableIterable<> {
        return contestRepository.findAll()
    }

    fun createContest(): String {
        return "Contest created"
    }

    fun getContestById(contestId: Long): String {
        return "Contest by id"
    }

    fun createContestById(contestId: Long): String {
        return "Contest created by id"
    }
}