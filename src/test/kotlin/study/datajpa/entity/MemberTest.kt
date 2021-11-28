package study.datajpa.entity

import org.junit.jupiter.api.Test
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceContext

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {
    @PersistenceContext
    private lateinit var em: EntityManager

    @Test
    public fun testEntity():Unit {
        val teamA = Team("웹개발팀")
        val teamB = Team("모바일개발팀")

        em.persist(teamA)
        em.persist(teamB)

        val member1: Member = Member("서두레박곤", 37, teamA)
        val member2: Member = Member("김두레박곤", 36, teamA)
        val member3: Member = Member("조두레박곤", 37, teamB)
        val member4: Member = Member("김윤태과장", 35, teamB)

        em.persist(member1)
        em.persist(member2)
        em.persist(member3)
        em.persist(member4)

        em.flush()
        em.clear()

        //확인
        val members = em.createQuery("select m from Member m", Member::class.java)
            .resultList

        for(m in members) {
            println("member = ${m.id}, ${m.username}, ${m.age}")
            println("member.team = ${m.team}")
        }


    }
}