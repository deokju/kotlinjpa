package study.datajpa.repository

import org.springframework.stereotype.Repository
import study.datajpa.entity.Member
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class MemberJpaRepository(
    @PersistenceContext
    val em: EntityManager

) {

    public fun  save(member: Member): Member{
        em.persist(member)
        return member;
    }

    public fun find(id: Long): Member{
        return em.find(Member::class.java, id)
    }

    fun delete(member: Member): Unit {
        em.remove(member)
    }

    fun findByIdConverNull(id: Long): Member {
        return em.find(Member::class.java, id)
    //return Optional.ofNullable(em.find(Member::class.java, id))
    }

    fun findById(id: Long): Optional<Member> {
        //return em.find(Member::class.java, id)
        return Optional.ofNullable(em.find(Member::class.java, id))
    }

    fun count():Long {
        return em.createQuery("select count(m) from Member m", Long::class.javaObjectType)
            .singleResult
    }

    fun findAll():List<Member> {
        //JPQL
        return em.createQuery("select m from Member m", Member::class.java)
            .resultList
    }

    fun findByUsernameAndGreaterThen(username: String, age: Int): List<Member> {
        return em.createQuery("select m from Member m where m.username =:username and m.age > :age", Member::class.java)
            .setParameter("username", username)
            .setParameter("age", age)
            .resultList
    }

    fun findByUsername(username: String): MutableList<Member> {
        return em.createNamedQuery("Member.findByUsername", Member::class.java)
            .setParameter("username", username)
            .resultList
    }
}