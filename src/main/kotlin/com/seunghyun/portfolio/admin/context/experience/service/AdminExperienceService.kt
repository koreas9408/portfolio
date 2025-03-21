package com.seunghyun.portfolio.admin.context.experience.service

import com.seunghyun.portfolio.admin.context.experience.form.ExperienceForm
import com.seunghyun.portfolio.admin.data.TableDTO
import com.seunghyun.portfolio.admin.exception.AdminBadRequestException
import com.seunghyun.portfolio.domain.entity.Experience
import com.seunghyun.portfolio.domain.entity.ExperienceDetail
import com.seunghyun.portfolio.domain.repository.ExperienceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminExperienceService(
    private val experienceRepository: ExperienceRepository
) {

    fun getExperienceTable(): TableDTO {
        val classInfo = Experience::class
        val entities = experienceRepository.findAll()

        return TableDTO.from(classInfo, entities, "details")
    }

    fun geExperienceDetailTable(id: Long?): TableDTO {
        val classInfo = ExperienceDetail::class
        val entities = if (id != null) experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: ExperienceForm) {
        val experienceDetails = form.details.map { detail -> detail.toEntity() }?.toMutableList()
        val experience = form.toEntity()
        experience.addDetails(experienceDetails)

        experienceRepository.save(experience)
    }

    @Transactional
    fun update(id: Long, form: ExperienceForm) {
        val experience = experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수 없습니다.") }

        experience.update(
            title = form.title,
            description = form.description,
            startYear = form.startYear,
            startMonth = form.startMonth,
            endYear = form.endYear,
            endMonth = form.endMonth,
            isActive = form.isActive
        )

        // {id, experienceDetail}
        val detailMap = experience.details.map { it.id to it }.toMap()

        form.details.forEach { detail ->
            val entity = detailMap[detail.id]
            if (entity != null) {
                entity.update(
                    content = detail.content,
                    isActive = detail.isActive
                )
            } else {
                experience.details.add(detail.toEntity())
            }
        }
    }
}