package ucb.judge.ujcontest.dao

import javax.persistence.*

@Entity
@Table(name = "student")
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    var studentId: Long = 0;

    @Column(name = "kc_uuid")
    var kcUuid: String = "";

    @Column(name = "status")
    var status: Boolean = true;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "campus_major_id", nullable = false)
//    var campusMajor: CampusMajor? = null;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
//    var studentSubjects: List<StudentSubject>? = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    var studentContests: List<StudentContest>? = null;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
//    var contestScoreboards: List<ContestScoreboard>? = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    var submissions: List<Submission>? = null;
}