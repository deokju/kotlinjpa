package study.datajpa.entity

import javax.persistence.*

@Entity
@NamedQuery(
    name="Member.findByUsername",
    query="select m from Member m where m.username = :username"
)
@NamedQuery(
    name="Member.findByAge",
    query="select m from Member m where m.age = :age"
)

class Member(
     val username: String ="김덕주",
     val age: Int = 0,
     @ManyToOne(fetch=FetchType.LAZY, cascade = [CascadeType.ALL])
     @JoinColumn(name="team_id")
     var team: Team?= null
) {
    init{
        if(team!= null){
            changeTeam(team!!)
        }
    }

    @Id @GeneratedValue
    var id: Long =0L

    fun changeTeam(team: Team):Unit{
        this.team = team;
        team.members.add(this)
    }
}