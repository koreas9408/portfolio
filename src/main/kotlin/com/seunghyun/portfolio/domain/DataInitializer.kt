package com.seunghyun.portfolio.domain

import com.seunghyun.portfolio.domain.constant.SkillType
import com.seunghyun.portfolio.domain.entity.*
import com.seunghyun.portfolio.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository
) {

    @PostConstruct
    fun initializeData() {
        println("스프링이 실행되었습니다. 테스트 데이터를 초기화합니다.")

        val achievements = mutableListOf<Achievement>(
            Achievement(
                title = "2022 Catkao 해커톤 최우수상",
                description = "고양이 쇼핑몰 검색 서비스의 아키텍처, 데이터 모델링",
                host = "캣카오",
                achievedDate = LocalDate.of(2022, 8, 20),
                isActive = true
            ), Achievement(
                title = "정보처리기사",
                description = "자료구조, 운영체제, 알고리즘, 데이터베이스 등",
                host = "한국산업인력공단",
                achievedDate = LocalDate.of(2020, 2, 20),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        val introductions = mutableListOf(
            Introduction(
                content = "주도적으로 문제를 찾고, 해결하는 고양이입니다.", isActive = true
            ), Introduction(
                content = "기술을 위한 기술이 아닌, 비즈니스 문제를 풀기 위한 기술을 추구합니다.", isActive = true
            ), Introduction(
                content = "기존 소스를 리팩토링하여 더 좋은 구조로 개선하는 작업을 좋아합니다.", isActive = true
            )
        )
        introductionRepository.saveAll(introductions)

        val links: MutableList<Link> = mutableListOf(
            Link(
                name = "GitHub", content = "https://github.com/infomuscle", isActive = true
            )
        )
        linkRepository.saveAll(links)

        val experience1 = Experience(
            title = "캣홀릭대학교(Catholic Univ.)",
            description = "컴퓨터공학 전공",
            startYear = 2022,
            startMonth = 3,
            endYear = 2024,
            endMonth = 2,
            isActive = true
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 4.3/4.5", isActive = true),
                ExperienceDetail(content = "소프트웨어 연구 학회 활동", isActive = true),
            )
        )
        val experience2 = Experience(
            title = "캣홀릭대학교(Catholic Univ.)",
            description = "컴퓨터공학 전공",
            startYear = 2022,
            startMonth = 3,
            endYear = 2024,
            endMonth = 2,
            isActive = true
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(content = "유기묘 위치 공유 서비스 개발", isActive = true),
                ExperienceDetail(content = "신입 교육 프로그램 우수상 수상", isActive = true),
            )
        )
        experienceRepository.saveAll(mutableListOf(experience1, experience2))

        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
        val python = Skill(name = "Python", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)
        val redis = Skill(name = "Redis", type = SkillType.DATABASE.name, isActive = true)
        val kafka = Skill(name = "Kafka", type = SkillType.TOOL.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, kotlin, python, spring, mysql, redis, kafka))

        val project1 = Project(
            name = "유기묘 발견 정보 공유 서비스",
            description = "",
            startYear = 2022,
            startMonth = 3,
            endYear = 2024,
            endMonth = 2,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(
                    content = "테스트1", url = "https://github.com/infomuscle", isActive = true
                )
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = mysql),
                ProjectSkill(project = project1, skill = redis)
            )
        )
        val project2 = Project(
            name = "유기묘 발견 정보 공유 서비스22",
            description = "",
            startYear = 2022,
            startMonth = 3,
            endYear = 2024,
            endMonth = 2,
            isActive = true
        )
        project2.addDetails(
            mutableListOf(
                ProjectDetail(
                    content = "테스트12222", url = "https://github.com/infomuscle", isActive = true
                )
            )
        )
        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = python),
                ProjectSkill(project = project2, skill = mysql),
                ProjectSkill(project = project2, skill = redis),
            )
        )
        projectRepository.saveAll(mutableListOf(project1, project2))

    }

}