package com.seunghyun.portfolio.domain.repository

import com.seunghyun.portfolio.domain.entity.Experience
import com.seunghyun.portfolio.domain.entity.ExperienceDetail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test

// JPA 관련 테스트
@DataJpaTest
// Test Instance의 라이프 사이클이 클래스 단위가 된다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExperienceRepositoryTest(
    @Autowired
    val experienceRepository: ExperienceRepository
) {
    val DATA_SIZE = 10

    private fun createExperience(n: Int): Experience {
        val experience = Experience(
            title = "$n",
            description = "테스트 설명 $n",
            startYear = 2022,
            startMonth = 3,
            endYear = 2024,
            endMonth = 2,
            isActive = true
        )

        val details = mutableListOf<ExperienceDetail>()
        for (i in 1..n) {
            val experienceDetail = ExperienceDetail(
                content = "테스트${i}",
                isActive = true
            )
            details.add(experienceDetail)
        }

        experience.addDetails(details)

        return experience
    }

    @BeforeAll
    fun beforeAll() {
        println("-------------- 테이터 초기화 이전 조회 시작 ---------------")
        val beforeInitialize = experienceRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        println("-------------- 테이터 초기화 이전 조회 종료 ---------------")

        println("-------------- 테이터 초기화 시작 ---------------")
        val experiences = mutableListOf<Experience>()
        for (i in 1..DATA_SIZE) {
            val experience = createExperience(i)
            experiences.add(experience)
        }
        experienceRepository.saveAll(experiences)
        println("-------------- 테이터 초기화 종료 ---------------")
    }

    @Test
    fun testFindAll() {
        println("----- findAll 테스트 시작 -----")
        val experiences = experienceRepository.findAll()
        println("experiences.size: ${experiences.size}")
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            println("experience.details.size: ${experience.details.size}")
            assertThat(experience.details).hasSize(experience.title.toInt())
        }
        println("----- findAll 테스트 종료 -----")
    }

    @Test
    fun testFindAllByIsActive() {
        println("----- findAll 테스트 시작 -----")
        val experiences = experienceRepository.findAllByIsActive(true)
        println("experiences.size: ${experiences.size}")
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            println("experience.details.size: ${experience.details.size}")
            assertThat(experience.details).hasSize(experience.title.toInt())
        }
        println("----- findAll 테스트 종료 -----")
    }
}