package ucb.judge.ujcontest.dao

import javax.persistence.*

@Entity
@Table(name = "admitted_language")
class AdmittedLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admitted_language_id")
    var admittedLanguageId: Long = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    var language: Language? = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    var problem: Problem? = null;

    @Column(name = "status")
    var status: Boolean = true;
}