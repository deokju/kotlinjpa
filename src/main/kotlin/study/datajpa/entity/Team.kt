package study.datajpa.entity

import javax.persistence.*

@Entity
class Team(
    var name: String="",
) {
    @Id @GeneratedValue
    @Column(name = "team_id")
    var id: Long = 0L

    @OneToMany(mappedBy = "team_id")
    val members: List<Member> = listOf()


}