package ucb.judge.ujcontest.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ucb.judge.ujcontest.dao.Student

interface StudentRepository  : JpaRepository<Student, Long> {
    fun findByStudentIdAndStatusIsTrue(studentId: Long): Student?
}
