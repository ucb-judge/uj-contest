package ucb.judge.ujcontest.dao

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "contest")
class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contest_id")
    var contestId: Long = 0;

    @Column(name = "name")
    var name: String = "";

    @Column(name = "description")
    var description: String = "";

    @Column(name = "starting_date")
    var startingDate: Timestamp = Timestamp(0);

    @Column(name = "ending_date")
    var endingDate: Timestamp = Timestamp(0);

    @Column(name = "status")
    var status: Boolean = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    var professor: Professor? = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    var subject: Subject? = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contest")
    var studentContests: List<StudentContest>? = null;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contest")
    var contestProblems: List<ContestProblem>? = null;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contest")
    var contestScoreboard : List<ContestScoreboard>? = null;

    @Column(name = "is_public")
    var isPublic: Boolean = false;
}