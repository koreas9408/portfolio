package com.seunghyun.portfolio.domain.repository

import com.seunghyun.portfolio.domain.constant.SkillType
import com.seunghyun.portfolio.domain.entity.Project
import com.seunghyun.portfolio.domain.entity.ProjectDetail
import com.seunghyun.portfolio.domain.entity.ProjectSkill
import com.seunghyun.portfolio.domain.entity.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectRepositoryTest(
    @Autowired
    val projectRepository: ProjectRepository,
    @Autowired
    val skillRepository: SkillRepository
) {
    val DATA_SIZE = 10

    private fun create(n: Int): Project {
        val project = Project(
            name = "$n",
            description = "테스트 설명 $n`",
            startYear = 2022,
            startMonth = 3,
            endYear = 2024,
            endMonth = 2,
            isActive = true
        )

        val details = mutableListOf<ProjectDetail>()
        for (i in 1..n) {
            val projectDetail = ProjectDetail(
                content = "테스트 $i",
                url = null,
                isActive = true
            )
            details.add(projectDetail)
        }
        project.addDetails(details)

        val skills = skillRepository.findAll()
        val skillsUsedInProject = skills.subList(0, n)
        for (skill in skillsUsedInProject) {
            val projectSkill = ProjectSkill(
                project = project,
                skill = skill
            )
            project.skills.add(projectSkill)
        }

        return project
    }

    @BeforeAll
    fun beforeAll() {
        println("----- 스킬 데이터 초기화 시작 -----")
        val skills = mutableListOf<Skill>()
        for (i in 1..DATA_SIZE) {
            val skillTypes = SkillType.values()
            val skill = Skill(name = "테스트 $i", type = skillTypes[i % skillTypes.size].name, isActive = true)
            skills.add(skill)
        }
        skillRepository.saveAll(skills)
        println("----- 스킬 데이터 초기화 종료 -----")

        println("-------------- 테이터 초기화 시작 ---------------")
        val project = mutableListOf<Project>()
        for (i in 1..DATA_SIZE) {
            val experience = create(i)
            project.add(experience)
        }
        projectRepository.saveAll(project)
        println("-------------- 테이터 초기화 종료 ---------------")
    }

    @Test
    fun testFindAll() {
        println("----- findAll 테스트 시작 -----")
        val projects = projectRepository.findAll()
        println("experiences.size: ${projects.size}")
        assertThat(projects).hasSize(DATA_SIZE)

        for (project in projects) {
            println("project.details.size: ${project.details.size}")
            assertThat(project.details).hasSize(project.details.size)

            println("project.skills.size: ${project.skills.size}")
            assertThat(project.skills).hasSize(project.skills.size)
        }
        println("----- findAll 테스트 종료 -----")
    }

    @Test
    fun testFindAllByIsActive() {
        println("----- findAll 테스트 시작 -----")
        val projects = projectRepository.findAllByIsActive(true)
        println("experiences.size: ${projects.size}")
        assertThat(projects).hasSize(DATA_SIZE)

        for (project in projects) {
            println("project.details.size: ${project.details.size}")
            assertThat(project.details).hasSize(project.details.size)
        }
        println("----- findAll 테스트 종료 -----")
    }
}