package ucb.judge.ujcontest.dao

import javax.persistence.*

@Entity
@Table(name = "professor")
class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    var professorId: Long = 0;

    @Column(name = "kc_uuid")
    var kcUuid: String = "";

    @Column(name = "status")
    var status: Boolean = true;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
//    var subjects: List<Subject>? = null;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
//    var problems: List<Problem>? = null;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
//    var contests: List<Contest>? = null;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
//    var clarifications: List<Clarification>? = null;
}