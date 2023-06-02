package ucb.judge.ujcontest.dto

data class ProfessorDto (
    val professorId: Long,
    val kcUuid : String,
    val subjects : List<SubjectDto>?,
    val problems : List<ProblemDto>?,
    val contests : List<ContestDto>?,
    val clarifications : List<ClarificationDto>?
){
    constructor( professorId: Long, kcUuid : String) : this(professorId, kcUuid, null, null, null, null)
    constructor( professorId: Long, kcUuid : String, subjects: List<SubjectDto>) : this(professorId, kcUuid, subjects, null, null, null)
}