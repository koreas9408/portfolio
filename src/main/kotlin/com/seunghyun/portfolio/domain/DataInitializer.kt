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
    private val experienceRepository: ExperienceRepository,
    private val accountRepository: AccountRepository
) {

    @PostConstruct
    fun initializeData() {
        println("스프링이 실행되었습니다. 테스트 데이터를 초기화합니다.")

        val achievements = mutableListOf(
            Achievement(
                title = "OCJA",
                description = "",
                host = "Oracle",
                achievedDate = LocalDate.of(2019, 12, 20),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        val introductions = mutableListOf(
            Introduction(
                content = "비즈니스의 문제를 해결하는 것에 집중하니다.", isActive = true
            ), Introduction(
                content = "동료들과 협업하는 것을 좋아하며, 함께 비즈니스 문제를 해결하고 성장하는 것을 좋아합니다.", isActive = true
            ), Introduction(
                content = "소프트웨어도 하나의 제품으로 생각하고 유지보수성을 고려하여 개발합니다.", isActive = true
            )
        )
        introductionRepository.saveAll(introductions)

        val links: MutableList<Link> = mutableListOf(
            Link(
                name = "GitHub", content = "https://github.com/koreas9408", isActive = true
            )
        )
        linkRepository.saveAll(links)

        val experience1 = Experience(
            title = "동양미래대학교",
            description = "컴퓨터정보공학 전공",
            startYear = 2017,
            startMonth = 3,
            endYear = 2020,
            endMonth = 2,
            isActive = true
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 3.7/4.5", isActive = true),
                ExperienceDetail(content = "2019년도 컴퓨터정보공학과 멘토링 멘토로 참여", isActive = true),
            )
        )

        val experience2 = Experience(
            title = "클비시스템",
            description = "개발팀/사원",
            startYear = 2020,
            startMonth = 1,
            endYear = 2021,
            endMonth = 1,
            isActive = true
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(content = "교육컨텐츠 개발 및 유지보수", isActive = true),
                ExperienceDetail(content = "Posco ICT RPA 프로젝트 Window Client 개발", isActive = true),
            )
        )

        val experience3 = Experience(
            title = "필드서비스",
            description = "개발팀/주임",
            startYear = 2022,
            startMonth = 5,
            endYear = 2024,
            endMonth = 3,
            isActive = true
        )
        experience3.addDetails(
            mutableListOf(
                ExperienceDetail(content = "통합 배송 백엔드 개발", isActive = true),
            )
        )

        val experience4 = Experience(
            title = "토스모바일",
            description = "개발팀/매니저",
            startYear = 2024,
            startMonth = 3,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        experience4.addDetails(
            mutableListOf(
                ExperienceDetail(content = "백오피스 시스템 개발 및 유지보수", isActive = true),
                ExperienceDetail(content = "AWS 시스템 설계", isActive = true),
            )
        )

        experienceRepository.saveAll(
            mutableListOf(experience1, experience2, experience3, experience4)
        )

        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
        val python = Skill(name = "Python", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)
        val oracle = Skill(name = "oracle", type = SkillType.DATABASE.name, isActive = true)
        val redis = Skill(name = "Redis", type = SkillType.DATABASE.name, isActive = true)
        val kafka = Skill(name = "Kafka", type = SkillType.TOOL.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, kotlin, python, spring, mysql, redis, kafka))

        val project1 = Project(
            name = "PMS 시스템 프로젝트",
            description = "쌍용교육센터 파이널 프로젝트",
            startYear = 2022,
            startMonth = 2,
            endYear = 2022,
            endMonth = 4,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(
                    content = "깃허브", url = "https://github.com/koreas9408/pms-project", isActive = true
                )
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = oracle)
            )
        )

        projectRepository.saveAll(mutableListOf(project1))

        val account =
            Account(loginId = "admin1", pw = "\$2a\$10\$f.J5ZmvZtzU7.GzUcZhgd.Cd3r0nhoA.MWTwzLj30IdY7A4FGQzm2")

        accountRepository.save(account)
    }

}