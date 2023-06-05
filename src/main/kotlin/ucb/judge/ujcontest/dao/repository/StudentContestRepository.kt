package ucb.judge.ujcontest.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ucb.judge.ujcontest.dao.StudentContest

interface StudentContestRepository : JpaRepository<StudentContest, Long> {
    fun findStudentsByContestContestId(contestId: Long): List<StudentContest>
    fun findByStudentStudentIdAndContestContestId(studentId: Long, contestId: Long): StudentContest?
}