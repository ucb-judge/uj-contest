package ucb.judge.ujcontest.dto

import java.util.Date

data class SubjectDto(
    var subjectId: Long,
//    var professor: ProfessorDto,
    var name: String,
    var code: String,
    var dateFrom: Date,
    var dateTo: Date,
)
