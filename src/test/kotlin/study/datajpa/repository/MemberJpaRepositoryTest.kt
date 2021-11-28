package study.datajpa.repository

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import study.datajpa.entity.Member

@SpringBootTest
@Transactional
@Rollback(false)
internal class MemberJpaRepositoryTest(
    @Autowired val memberJpaRepository: MemberJpaRepository
) {

    @Test
    fun save() {
        val member = Member(username = "김덕주")
        val member1 = memberJpaRepository.save(member)


        val findMember = memberJpaRepository.find(member1.id)
        Assertions.assertThat(findMember.id).isEqualTo(member.id)
        Assertions.assertThat(findMember.username).isEqualTo(member.username)

    }

    @Test
    fun basicCRUD() {
        val member1 = Member(username = "김덕주")
        val member2 = Member(username = "서승현")

        memberJpaRepository.save(member1)
        memberJpaRepository.save(member2)

        val findMember1 = memberJpaRepository.findById(member1.id).get()
        val findMember2 = memberJpaRepository.findById(member2.id).get()

        assertThat(findMember1).isEqualTo(member1)
        assertThat(findMember2).isEqualTo(member2)

        val all = memberJpaRepository.findAll()
        assertThat(all.size).isEqualTo(2)

        val count = memberJpaRepository.count()
        assertThat(count).isEqualTo(2)

        memberJpaRepository.delete(member1)
        memberJpaRepository.delete(member2)

        val deletedCount = memberJpaRepository.count()
        assertThat(deletedCount).isEqualTo(0)
    }

    @Test
    fun findByusernameAndAgeGreaterThen() {
        val m1 = Member(username="김덕주", 36)
        val m2 = Member(username="서승현", age=37)

        memberJpaRepository.save(m1)
        memberJpaRepository.save(m2)

        val result = memberJpaRepository.findByUsernameAndGreaterThen("김덕주", 35)

        assertThat(result.get(0).username).isEqualTo("김덕주")
        assertThat(result.get(0).age).isEqualTo(36)

    }

    @Test
    fun testNamedQuery() {
        val m1 = Member(username="김덕주", 36)
        val m2 = Member(username="서승현", age=37)

        memberJpaRepository.save(m1)
        memberJpaRepository.save(m2)

        val tov = memberJpaRepository.findByUsername("서승현")
        val findMember = tov.get(0)

        assertThat(findMember).isEqualTo(m2)
    }
}