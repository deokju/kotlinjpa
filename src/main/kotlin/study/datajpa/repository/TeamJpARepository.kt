package study.datajpa.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.datajpa.entity.Member
import study.datajpa.entity.Team
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class TeamJpARepository
(
    @PersistenceContext
    private val em: EntityManager
){
    fun save(team: Team): Team {
        em.persist(team)
        return team
    }

    fun delete(team: Team) : Unit {
       em.remove(team)
    }

    fun findById(id: Long): Optional<Team> {
        val member = em.find(Team::class.java, id)
        return Optional.ofNullable(member)
    }

    fun count(): Long {
        return em.createQuery("select count(t) from Team t", Long::class.java)
            .singleResult
    }


    fun findAll():MutableList<Team>{
        return em.createQuery("select t from Team t", Team::class.java)
            .resultList
    }

}