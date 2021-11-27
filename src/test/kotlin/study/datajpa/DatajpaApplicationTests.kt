package study.datajpa

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import study.datajpa.entity.Member

@SpringBootTest
class DatajpaApplicationTests {

    @Test
    fun contextLoads() {
        val member = Member( username = "김덕주")

    }

}
