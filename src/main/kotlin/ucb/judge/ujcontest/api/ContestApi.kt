package ucb.judge.ujcontest.api

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ucb.judge.ujcontest.bl.ContestBl
import ucb.judge.ujcontest.dto.ContestDto
import ucb.judge.ujcontest.dto.ProblemDto
import ucb.judge.ujcontest.dto.ResponseDto

@RestController
@RequestMapping("/api/v1/contest")
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
        logger.info("POST /contest endpoint reached")
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
        logger.info("GET /contest/$contestId endpoint reached")
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
        logger.info("PUT /contest/$contestId endpoint reached")
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
        logger.info("DELETE /contest/$contestId endpoint reached")
        return ResponseEntity.ok(
            ResponseDto.success(
                contestBl.deleteContest(contestId)
            )
        )
    }

//    //FIXME: This endpoint is not working
//    @GetMapping("/{contestId}/problems")
//    fun getProblemsByContestId(
//        @PathVariable contestId: Long
//    ): ResponseEntity<ResponseDto<List<ProblemDto>>> {
//        logger.info("GET /contest/$contestId/problems endpoint reached")
//        return ResponseEntity.ok(
//            ResponseDto.success(
//                contestBl.getProblemsByContestId(contestId)
//            )
//        )
//    }



//    @GetMapping("/{contestId}/scoreboard")
//    fun getScoreboardByContestId(
//        @PathVariable contestId: Long
//    ): ResponseEntity<ResponseDto<List<ContestScoreboardDto>>> {
//        logger.info("GET /contest/$contestId/scoreboard endpoint reached")
//        return ResponseEntity.ok(
//            ResponseDto.success(
//                contestBl.getScoreboardByContestId(contestId)
//            )
//        )
//    }

}