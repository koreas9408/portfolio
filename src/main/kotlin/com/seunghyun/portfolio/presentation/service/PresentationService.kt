package com.seunghyun.portfolio.presentation.service

import com.seunghyun.portfolio.presentation.dto.IntroductionDTO
import com.seunghyun.portfolio.presentation.dto.LinkDTO
import com.seunghyun.portfolio.presentation.dto.ProjectDTO
import com.seunghyun.portfolio.presentation.dto.ResumeDTO
import com.seunghyun.portfolio.presentation.respository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository
) {

    // readOnly : 읽기 전용 명시
    // JPA는 스냅샷을 뜨고 트랜잭션이 끝났을때 스냅샷 전/후를 비교해서 update 후 commit을 하는데 읽기전용을 지정하면 스냅샷을 뜨지않아 성능 이점이 있다.
    @Transactional(readOnly = true)
    fun getIntroductions(): List<IntroductionDTO> {
        val introductions = presentationRepository.getActiveIntroductions()

        return introductions.map { IntroductionDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getLinks(): List<LinkDTO> {
        val links = presentationRepository.getActiveLinks()

        return links.map { LinkDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getResume(): ResumeDTO {
        val experiences = presentationRepository.getActiveExperiences()
        val achievements = presentationRepository.getActiveAchievements()
        val skills = presentationRepository.getActiveSkills()

        return ResumeDTO(
            experiences = experiences,
            achievements = achievements,
            skills = skills
        )
    }

    @Transactional(readOnly = true)
    fun getProjects(): List<ProjectDTO> {
        val projects = presentationRepository.getActiveProjects()

        return projects.map { ProjectDTO(it) }
    }

}