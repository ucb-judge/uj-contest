package ucb.judge.ujcontest.api

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ucb.judge.ujcontest.bl.ContestBl
import ucb.judge.ujcontest.dto.*

@RestController
@RequestMapping("/api/v1/contests")
class ContestApi @Autowired constructor(
    private val contestBl: ContestBl,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(ContestApi::class.java)
    }

    /**
     * Get all contests
     * @return List of contests
    */
    @GetMapping("")
    fun getContest(): ResponseEntity<ResponseDto<List<ContestDto>>> {
        logger.info("GET /contests endpoint reached")
        return ResponseEntity.ok(
            ResponseDto.success(
                contestBl.getContests()
            )
        )
    }

    /**
     * Create a contest
     * @return Contest created
    */
    @PostMapping("")
    fun createContest(
        @RequestBody contestDto: ContestDto
    ): ResponseEntity<ResponseDto<Long>> {
        logger.info("POST /contests endpoint reached")
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseDto.success(
                contestBl.createContest(contestDto)
            )
        )
    }

    /**
     * Get contest by id
     * @param contestId
     * @return Contest
    */
    @GetMapping("/{contestId}")
    fun getContestById(
        @PathVariable contestId: Long
    ): ResponseEntity<ResponseDto<ContestDto>> {
        logger.info("GET /contests/$contestId endpoint reached")
        return ResponseEntity.ok(
            ResponseDto.success(
                contestBl.getContestById(contestId)
            )
        )
    }

    /**
     * Update contest
     * @param contestId
     * @return Contest updated
    */
    @PutMapping("/{contestId}")
    fun updateContest(
        @PathVariable contestId: Long,
        @RequestBody contestDto: ContestDto
    ): ResponseEntity<ResponseDto<Long>> {
        logger.info("PUT /contests/$contestId endpoint reached")
        return ResponseEntity.ok(
            ResponseDto.success(
                contestBl.updateContest(contestId, contestDto)
            )
        )
    }

    /**
     * Delete contest
     * @param contestId
     * @return Contest delete
    */
    @DeleteMapping("/{contestId}")
    fun deleteContest(
        @PathVariable contestId: Long
    ): ResponseEntity<ResponseDto<Long>> {
        logger.info("DELETE /contests/$contestId endpoint reached")
        return ResponseEntity.ok(
            ResponseDto.success(
                contestBl.deleteContest(contestId)
            )
        )
    }

    /**
     * Sign up to contest
     * @param contestId
    */

    @PostMapping("/{contestId}/signup")
    fun signUpToContest(
        @PathVariable contestId: Long
    ): ResponseEntity<ResponseDto<Long>> {
        logger.info("POST /contests/$contestId/signup endpoint reached")
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseDto.success(
                contestBl.signUpToContest(contestId)
            )
        )
    }

    @PostMapping("/{contestId}/register/{kcUuid}")
    fun registerToContest(
        @PathVariable kcUuid: String,
        @PathVariable contestId: Long
    ): ResponseEntity<ResponseDto<Long>> {
        logger.info("POST /contests/$contestId/register endpoint reached")
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseDto.success(
                contestBl.registerToContest(contestId, kcUuid)
            )
        )
    }

    @GetMapping("/{contestId}/participants")
    fun getParticipantsByContestId(
        @PathVariable contestId: Long
    ): ResponseEntity<ResponseDto<List<StudentDto>>> {
        logger.info("GET /contests/$contestId/participants endpoint reached")
        return ResponseEntity.ok(
            ResponseDto.success(
                contestBl.getParticipantsByContestId(contestId)
            )
        )
    }

    @GetMapping("/{contestId}/problems")
    fun getProblemsByContestId(
        @PathVariable contestId: Long
    ): ResponseEntity<ResponseDto<List<ProblemDto>>> {
        logger.info("GET /contests/$contestId/problems endpoint reached")
        return ResponseEntity.ok(
            ResponseDto.success(
                contestBl.getProblemsByContestId(contestId)
            )
        )
    }

    @PostMapping("/{contestId}/problems/{problemId}")
    fun addProblemToContest(
        @PathVariable contestId: Long,
        @PathVariable problemId: Long
    ): ResponseEntity<ResponseDto<Long>> {
        logger.info("POST /contests/$contestId/problems endpoint reached")
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseDto.success(
                contestBl.addProblemToContest(contestId, problemId)
            )
        )
    }

    @GetMapping("/{contestId}/scoreboard")
    fun getScoreboardByContestId(
        @PathVariable contestId: Long
    ): ResponseEntity<ResponseDto<List<ContestScoreboardDto>>> {
        logger.info("GET /contests/$contestId/scoreboard endpoint reached")
        return ResponseEntity.ok(
            ResponseDto.success(
                contestBl.getScoreboard(contestId)
            )
        )
    }

    @GetMapping("/{contestId}/validate")
    fun validateContestSubmission(
        @PathVariable contestId: Long,
        @RequestParam(name = "kcUuid") kcUuid: String,
        @RequestParam(name = "problemId") problemId: Long
    ): ResponseEntity<ResponseDto<Long>> {
        logger.info("GET /contests/$contestId/validate endpoint reached")
        return ResponseEntity.ok(
            ResponseDto(
                data = contestBl.validateContestSubmission(contestId, problemId, kcUuid),
                message = "Contest submission validated successfully",
                successful = true
            )
        )
    }

}