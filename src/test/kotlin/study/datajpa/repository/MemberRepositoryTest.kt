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

}