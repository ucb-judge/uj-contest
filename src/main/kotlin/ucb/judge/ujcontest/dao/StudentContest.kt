package ucb.judge.ujcontest.dao

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "student_contest")
class StudentContest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_contest_id")
    var studentContestId: Long = 0;

    @Column(name = "registered_on")
    var registeredOn: Timestamp = Timestamp(System.currentTimeMillis());

    @Column(name = "status")
    var status: Boolean = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    var student: Student? = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id")
    var contest: Contest? = null;

    @OneToMany(mappedBy = "studentContest")
    var clarifications: List<Clarification>? = null;
}