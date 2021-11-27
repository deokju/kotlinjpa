package study.datajpa.repository

import org.springframework.stereotype.Repository
import study.datajpa.entity.Member
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
}