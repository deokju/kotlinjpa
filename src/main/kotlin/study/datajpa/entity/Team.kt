package study.datajpa.entity

import javax.persistence.*

@Entity
class Team(
    var name: String="",
) {
    @Id @GeneratedValue
    @Column(name = "team_id")
    var id: Long = 0L

    @OneToMany(mappedBy = "team")
    val members: MutableList<Member> = mutableListOf()


}