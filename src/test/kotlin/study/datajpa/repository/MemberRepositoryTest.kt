package study.datajpa.repository

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import study.datajpa.entity.Member

@SpringBootTest
@Transactional
@Rollback(false)
internal class MemberRepositoryTest(
    @Autowired val memberRepository: MemberRepository
) {

    @Test
    fun `멤버 테스트`() {
        val member = Member(username = "서승현")
        val member1 = memberRepository.save(member)


        val findMember = memberRepository.findById(member1.id)
        val realMember = findMember.get()

        Assertions.assertThat(realMember.id).isEqualTo(member.id)
        Assertions.assertThat(realMember.username).isEqualTo(member.username)

    }

    @Test
    fun basicCRUD() {
        val member1 = Member(username = "김덕주")
        val member2 = Member(username = "서승현")

        memberRepository.save(member1)
        memberRepository.save(member2)

        val findMember1 = memberRepository.findById(member1.id).get()
        val findMember2 = memberRepository.findById(member2.id).get()

        Assertions.assertThat(findMember1).isEqualTo(member1)
        Assertions.assertThat(findMember2).isEqualTo(member2)

        val all = memberRepository.findAll()
        Assertions.assertThat(all.size).isEqualTo(2)

        val count = memberRepository.count()
        Assertions.assertThat(count).isEqualTo(2)

        memberRepository.delete(member1)
        memberRepository.delete(member2)

        val deletedCount = memberRepository.count()
        Assertions.assertThat(deletedCount).isEqualTo(0)
    }

    @Test
    fun findByusernameAndAgeGreaterThen() {
        val m1 = Member(username="김덕주", 36)
        val m2 = Member(username="서승현", age=37)

        memberRepository.save(m1)
        memberRepository.save(m2)

        val result = memberRepository.findByUsernameAndAgeGreaterThan("김덕주", 35)

        Assertions.assertThat(result.get(0).username).isEqualTo("김덕주")
        Assertions.assertThat(result.get(0).age).isEqualTo(36)
    }

    @Test
    fun testNamedQuery() {
        val m1 = Member(username="김덕주", 36)
        val m2 = Member(username="서승현", age=37)

        memberRepository.save(m1)
        memberRepository.save(m2)

        val tov = memberRepository.findByUsername("서승현")
        val findMember = tov.get(0)

        Assertions.assertThat(findMember).isEqualTo(m2)
    }

    @Test
    fun `나이로 네임드쿼리테스트`() {
        val m1 = Member(username="김덕주", 36)
        val m2 = Member(username="서승현", age=37)

        memberRepository.save(m1)
        memberRepository.save(m2)

        val tov = memberRepository.findByAge(37)
        val findMember = tov.get(0)

        Assertions.assertThat(findMember).isEqualTo(m2)
    }

    @Test
    fun `JPQL 테스트`() {
        val m1 = Member(username="김덕주", 36)
        val m2 = Member(username="서승현", age=37)

        memberRepository.save(m1)
        memberRepository.save(m2)

        val tov = memberRepository.findUser("서승현",37)
        val findMember = tov.get(0)

        Assertions.assertThat(findMember).isEqualTo(m2)
    }
}