package ucb.judge.ujcontest.bl

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ucb.judge.ujcontest.dao.repository.ContestRepository
import ucb.judge.ujcontest.dao.repository.ProfessorRepository
import ucb.judge.ujcontest.dto.ContestDto
import ucb.judge.ujcontest.exception.UjNotFoundException
import ucb.judge.ujcontest.mapper.ContestMapper
import ucb.judge.ujcontest.mapper.ProfessorMapper
import ucb.judge.ujcontest.mapper.impl.ContestMapperImpl
import ucb.judge.ujcontest.mapper.impl.ProfessorMapperImpl
import ucb.judge.ujcontest.service.UjUsersService
import ucb.judge.ujcontest.util.KeycloakSecurityContextHolder


@Service
class ContestBl @Autowired constructor(
    private val contestRepository: ContestRepository,
//    private val problemRepository: ProblemRepository,
//    private val contestProblemRepository: ContestProblemRepository,
    private val professorRepository: ProfessorRepository,
    private val ujUsersService: UjUsersService,
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

        val kcUuid = KeycloakSecurityContextHolder.getSubject() ?: throw UjNotFoundException("User not found")

        val token = "Bearer ${keycloakBl.getToken()}"

        val professorId = ujUsersService.getProfessorByKcUuid(kcUuid, token).data ?: throw UjNotFoundException("Professor not found")

        val professor = professorRepository.findByProfessorIdAndStatusIsTrue(professorId) ?: throw UjNotFoundException("Professor not found")
        val professorMapper : ProfessorMapper = ProfessorMapperImpl()
        contestDto.professor = professorMapper.toDto(professor)

        val contestMapper: ContestMapper = ContestMapperImpl()
        return contestRepository.save(
            contestMapper.toEntity(contestDto)
        ).contestId
    }

    fun updateContest(contestId: Long, contestDto: ContestDto): Long {
        logger.info("Update contest Business Logic initiated")

        val contest = contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
        val kcUuid = KeycloakSecurityContextHolder.getSubject() ?: throw UjNotFoundException("User not found")
        val token = "Bearer ${keycloakBl.getToken()}"
        val professorId = ujUsersService.getProfessorByKcUuid(kcUuid, token).data ?: throw UjNotFoundException("Professor not found")

        val professor = professorRepository.findByProfessorIdAndStatusIsTrue(professorId) ?: throw UjNotFoundException("Professor not found")
        val professorMapper : ProfessorMapper = ProfessorMapperImpl()
        contestDto.professor = professorMapper.toDto(professor)



        val contestMapper: ContestMapper = ContestMapperImpl()
        val updatedContest = contestMapper.toEntity(contestDto)
        updatedContest.contestId = contest.contestId
        return contestRepository.save(updatedContest).contestId
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



//    fun getProblemsByContestId(contestId: Long): List<ProblemDto> { // TODO: COMUNICATE WITH uj-problems
//        logger.info("Get problems by contest id Business Logic initiated")
//        contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
//        val problems = contestProblemRepository.findAllByContestId(contestId)
//        val problemMapper: ProblemMapper = ProblemMapperImpl()
//        return problems.map {problem -> problemMapper.toDto(problem) }
//    }

//    fun getScoreboardByContestId(contestId: Long): List<ProblemDto> {
//        logger.info("Get scoreboard by contest id Business Logic initiated")
//        contestRepository.findByContestIdAndStatusIsTrue(contestId) ?: throw UjNotFoundException("Contest not found")
//        val problems = contestProblemRepository.findAllByContestId(contestId)
//        val problemMapper: ProblemMapper = ProblemMapperImpl()
//        return problems.map {problem -> problemMapper.toDto(problem) }
//    }
}