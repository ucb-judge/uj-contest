package ucb.judge.ujcontest.dto

import ucb.judge.ujcontest.dao.Professor
import ucb.judge.ujcontest.dao.Subject
import java.sql.Timestamp

data class ContestDto(
    var contestId: Long,
    var name: String,
    var description: String,
    var startDate: Timestamp,
    var endDate: Timestamp,
    var professor: ProfessorDto? = null,
    var subject: SubjectDto? = null,

    // TODO: Check if studentContests, contestProblems, contestScoreboard are needed

    var isPublic: Boolean,
)