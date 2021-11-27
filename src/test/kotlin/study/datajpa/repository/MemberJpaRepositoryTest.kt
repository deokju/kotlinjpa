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
    fun find() {
    }
}