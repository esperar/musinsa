package hope.musinsa.global.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDSLConfig(
    private val em : EntityManager
) {

    @Bean
    fun queryFactory(): JPAQueryFactory = JPAQueryFactory(em)
}