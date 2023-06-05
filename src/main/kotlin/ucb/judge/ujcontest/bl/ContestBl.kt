package ucb.judge.ujcontest.bl

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ucb.judge.ujcontest.dao.*
import ucb.judge.ujcontest.dao.repository.*
import ucb.judge.ujcontest.dto.*
import ucb.judge.ujcontest.exception.UjBadRequestException
import ucb.judge.ujcontest.exception.UjForbiddenException
import ucb.judge.ujcontest.exception.UjNotFoundException
import ucb.judge.ujcontest.mapper.ContestMapper
import ucb.judge.ujcontest.mapper.ProfessorMapper
import ucb.judge.ujcontest.mapper.StudentMapper
import ucb.judge.ujcontest.mapper.impl.ContestMapperImpl
import ucb.judge.ujcontest.mapper.impl.ProfessorMapperImpl
import ucb.judge.ujcontest.mapper.impl.StudentMapperImpl
import ucb.judge.ujcontest.service.UjProblemsService
import ucb.judge.ujcontest.service.UjUsersService
import ucb.judge.ujcontest.util.KeycloakSecurityContextHolder
import java.util.*


@Service
class ContestBl @Autowired constructor(
    private val contestRepository: ContestRepository,
    private val professorRepository: ProfessorRepository,
    private val studentRepository: StudentRepository,
    private val studentContestRepository: StudentContestRepository,
    private val contestProblemRepository: ContestProblemRepository,
    private val problemRepository: ProblemRepository,
    private val contestScoreboardRepository: ContestScoreboardRepository,
    private val ujUsersService: UjUsersService,
    private val ujProblemsService: UjProblemsService,
    private val keycloakBl: KeycloakBl
){
    companion object {
        private val logger = LoggerFactory.getLogger(ContestBl::class.java)
    }

    fun getContests(): List<ContestDto> {
        logger.info("Get contests Business Logic initiated")
        val contests = contestRepository.findAllByStatusIsTrue()
        val contestMapper: ContestMapper = ContestMapperImpl()
        return contests.map {contest -> contestMapper.toDto(contest) }
    }

    fun createContest(contestDto: ContestDto): Long {
        logger.info("Create contest Business Logic initiated")
        contestDto.professor = getProfessorDto(contestDto.professor?.kcUuid)
        val contestMapper: ContestMapper = ContestMapperImpl()
        return contestRepository.save(
            contestMapper.toEntity(contestDto)
        ).contestId
    }

    fun updateContest(contestId: Long, contestDto: ContestDto): Long {
        logger.info("Update contest Business Logic initiated")
        val contest = contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        contestDto.professor = getProfessorDto(contestDto.professor?.kcUuid)
        val contestMapper: ContestMapper = ContestMapperImpl()
        val updatedContest = contestMapper.toEntity(contestDto)
        updatedContest.contestId = contest.contestId
        return contestRepository.save(updatedContest).contestId
    }

    fun getProfessorDto(kcUuid: String?): ProfessorDto{
        val professorKcUuid = kcUuid ?: KeycloakSecurityContextHolder.getSubject() ?: throw UjNotFoundException("User not found")
        val token = "Bearer ${keycloakBl.getToken()}"
        val professorId = ujUsersService.getProfessorByKcUuid(professorKcUuid, token).data ?: throw UjNotFoundException("Professor not found")
        val professor = professorRepository.findByProfessorIdAndStatusIsTrue(professorId) ?: throw UjNotFoundException("Professor not found")
        val professorMapper : ProfessorMapper = ProfessorMapperImpl()
        return professorMapper.toDto(professor)
    }
    fun getContestById(contestId: Long): ContestDto {
        logger.info("Get contest by id Business Logic initiated")
        val contest = contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val contestMapper: ContestMapper = ContestMapperImpl()
        return contestMapper.toDto(contest)
    }

    fun deleteContest(contestId: Long): Long {
        logger.info("Delete contest Business Logic initiated")
        val contest = contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        contest.status = false
        return contestRepository.save(contest).contestId
    }

    fun signUpToContest(contestId: Long) : Long {
        logger.info("Sign up to contest Business Logic initiated")
        contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val kcUuid = KeycloakSecurityContextHolder.getSubject() ?: throw UjNotFoundException("User not found")
        val newStudentContest = getNewStudentContest(contestId, kcUuid)
        if (!newStudentContest.contest!!.isPublic){
            throw UjNotFoundException("Contest not public")
        }
        studentContestRepository.save(newStudentContest)
        addToContestScoreboard(newStudentContest.contest!!, newStudentContest.student!!)
        logger.info("Sign up to contest Business Logic finished")
        return newStudentContest.studentContestId
    }

    fun registerToContest(contestId: Long, kcUuid:String): Long {
        logger.info("Register to contest Business Logic initiated")
        val newStudentContest = getNewStudentContest(contestId, kcUuid)
        if (newStudentContest.contest!!.isPublic){
            throw UjNotFoundException("Contest is public")
        }
        studentContestRepository.save(newStudentContest)
        addToContestScoreboard(newStudentContest.contest!!, newStudentContest.student!!)
        logger.info("Sign up to contest Business Logic finished")
        return newStudentContest.studentContestId
    }

    fun addToContestScoreboard(contest:Contest, student: Student) :Long {
        val contestScoreboard = ContestScoreboard()
        contestScoreboard.student = student
        contestScoreboard.contest = contest
        contestScoreboard.rank = 0
        contestScoreboard.problemsSolved = 0
        contestScoreboardRepository.save(contestScoreboard)
        return contestScoreboard.contestScoreboardId
    }

    fun getNewStudentContest(contestId: Long, kcUuid: String) : StudentContest{
        contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val token = "Bearer ${keycloakBl.getToken()}"
        val studentId = ujUsersService.getStudentByKcUuid(kcUuid, token).data ?: throw UjNotFoundException("Student not found")
        val studentContest = studentContestRepository.findByStudentStudentIdAndContestContestId(studentId, contestId)
        if (studentContest != null) {
            throw UjNotFoundException("Student already registered")
        }
        val student = studentRepository.findByStudentIdAndStatusIsTrue(studentId) ?: throw UjNotFoundException("Student not found")
        val contest = contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val newStudentContest = StudentContest()
        newStudentContest.student = student
        newStudentContest.contest = contest
        return newStudentContest
    }

    fun getParticipantsByContestId(contestId: Long): List<StudentDto> {
        logger.info("Get participants by contest id Business Logic initiated")
        contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val participants = studentContestRepository.findStudentsByContestContestId(contestId);
        println(participants.toString())
        val studentMapper: StudentMapper = StudentMapperImpl()
        return participants.map {participant -> studentMapper.toDto(participant.student!!) }
    }

    fun getProblemsByContestId(contestId: Long): List<ProblemDto> {
        logger.info("Get problems by contest id Business Logic initiated")
        contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val problems = contestProblemRepository.findAllByContestContestId(contestId)

        val token = "Bearer ${keycloakBl.getToken()}"

        return problems.map { problem ->
            ujProblemsService.getProblemById(problem.problem!!.problemId, token).data ?: throw UjNotFoundException("Problem not found")
        }
    }

    fun addProblemToContest(contestId: Long, problemId: Long): Long {
        logger.info("Add problem to contest Business Logic initiated")
        val contest = contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val problem = ujProblemsService.getProblemById(problemId, "Bearer ${keycloakBl.getToken()}").data ?: throw UjNotFoundException("Problem not found")
        val newProblem = problemRepository.findById(problem.problemId)
        val contestProblem = ContestProblem()
        contestProblem.contest = contest
        contestProblem.problem = newProblem.get()
        val contestProblemExists = contestProblemRepository.findByContestContestIdAndProblemProblemId(contestId, problemId)
        if (contestProblemExists != null) {
            throw UjBadRequestException("Problem already exists in contest")
        }
        return contestProblemRepository.save(contestProblem).contestProblemId
    }

    fun getScoreboard(contestId: Long) : List<ContestScoreboardDto> {
        val token = "Bearer ${keycloakBl.getToken()}"
        logger.info("Get scoreboard by contest id Business Logic initiated")
        contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val scoreboard = contestScoreboardRepository.findByContestContestId(contestId)
        return scoreboard.map{ score ->
            val user = ujUsersService.getProfile(score.student!!.kcUuid, token).data ?: throw UjNotFoundException("Student not found")
            val userDetailsDto = UserDetailsDto(
                userId = 0,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email
            );
            val contestScoreboardDto = ContestScoreboardDto(
                student = userDetailsDto,
                problemsSolved = score.problemsSolved,
            )
            contestScoreboardDto
        }
    }

    fun validateContestSubmission(contestId: Long, problemId: Long, kcUuid: String): Long {
        try {
            logger.info("Validate contest submission Business Logic initiated")
            contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
            val token = "Bearer ${keycloakBl.getToken()}"
            logger.info("Getting student id from uj-users")
            val studentId = ujUsersService.getStudentByKcUuid(kcUuid, token).data ?: throw UjNotFoundException("Student not found")
            logger.info("Getting student contest")
            val studentContest = studentContestRepository.findByStudentStudentIdAndContestContestId(studentId, contestId)
            if(studentContest == null) {
                logger.info("student not in contest")
                throw UjForbiddenException("Student not in contest")
            } else {
                logger.info("Getting contest problem")
                val contestProblem = contestProblemRepository.findByContestContestIdAndProblemProblemId(contestId, problemId)
                if(contestProblem == null) {
                    logger.info("problem not in contest")
                    throw UjForbiddenException("Problem not in contest")
                }
                logger.info(contestProblem.contestProblemId.toString())
                return contestProblem.contestProblemId
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw UjForbiddenException("Error processing request")
        }
    }
}