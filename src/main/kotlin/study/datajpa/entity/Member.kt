package study.datajpa.entity

import javax.persistence.*

@Entity
class Member(
     val username: String ="김덕주",
     val age: Int = 0,
     @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name="team_id")
     var team: Team = Team()
) {
    init{
        changeTeam(team)
    }

    @Id @GeneratedValue
    var id: Long =0L

    fun changeTeam(team: Team):Unit{
        this.team = team;
    }
}