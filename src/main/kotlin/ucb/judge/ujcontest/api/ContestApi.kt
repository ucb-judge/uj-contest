package ucb.judge.ujcontest.api

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ucb.judge.ujcontest.bl.ContestBl
import ucb.judge.ujcontest.dao.Contest
import ucb.judge.ujcontest.dto.ResponseDto

@RestController
@RequestMapping("/api/v1/contest")
class ContestApi @Autowired constructor(
    private val contestBl: ContestBl
) {
    companion object {
        private val logger = LoggerFactory.getLogger(ContestApi::class.java)
    }


    /**
     * Get all contests
     * @return List of contests
    */
    @GetMapping("")
    fun getContest(): ResponseEntity<ResponseDto<MutableIterable<ContestDto>>> {
        logger.info("Get contests")
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
    fun createContest(): String {
        logger.info("Create contest")
        return "Contest created"
    }

    /**
     * Get contest by id
     * @param contestId
     * @return Contest
    */
    @GetMapping("/{contestId}")
    fun getContestById(
        @PathVariable contestId: Long
    ): String {
        logger.info("Get contest by id")
        return "Contest by id"
    }

    /**
     * Create a contest by id
     * @param contestId
     * @return Contest created by id
    */
    @PostMapping("/{contestId}")
    fun createContestById(
        @PathVariable contestId: Long
    ): String {
        logger.info("Create contest by id")
        return "Contest created by id"
    }

    /**
     * Update contest
     * @param contestId
     * @return Contest updated
    */
    @PutMapping("/{contestId}")
    fun updateContest(
        @PathVariable contestId: Long
    ): String {
        logger.info("Update contest")
        return "Contest updated"
    }

    /**
     * Delete contest
     * @param contestId
     * @return Contest delete
    */
    @DeleteMapping("/{contestId}")
    fun deleteContest(
        @PathVariable contestId: Long
    ): String {
        logger.info("Delete contest")
        return "Contest deleted"
    }
}