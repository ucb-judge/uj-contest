package ucb.judge.ujcontest.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import ucb.judge.ujcontest.dto.ProblemDto
import ucb.judge.ujcontest.dto.ResponseDto

@FeignClient(name = "uj-problems")
interface UjProblemsService {
    @GetMapping("/api/v1/problems/{id}")
    fun getProblemById(
        @RequestParam("id", required = true) id: Long,
        @RequestHeader("Authorization") token: String
    ):ResponseDto<ProblemDto>

    @GetMapping("/api/v1/problems/{id}/exists")
    fun existsProblemById(
        @RequestParam("id", required = true) id: Long,
        @RequestHeader("Authorization") token: String
    ):Boolean
}