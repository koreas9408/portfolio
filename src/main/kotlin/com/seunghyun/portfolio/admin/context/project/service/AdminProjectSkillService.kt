package com.seunghyun.portfolio.admin.context.project.service

import com.seunghyun.portfolio.admin.data.TableDTO
import com.seunghyun.portfolio.domain.repository.ProjectRepository
import com.seunghyun.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminProjectSkillService(
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository
) {

    @Transactional
    fun getProjectSkillTable(): TableDTO {

        val projects = projectRepository.findAll()
        val column = mutableListOf<String>(
            "id", "projectId", "projectName", "skillId", "skillName", "createdDateTime", "updatedDateTime"
        )
        val records = mutableListOf<MutableList<String>>()
        projects.forEach {
            it.skills.forEach { skill ->
                val record = mutableListOf<String>()
                record.add(skill.id.toString())
                record.add(skill.project.id.toString())
                record.add(skill.project.name)
                record.add(skill.skill.id.toString())
                record.add(skill.skill.name)
                record.add(skill.createdDateTime.toString())
                record.add(skill.modifiedDateTime.toString())
                records.add(record)
            }
        }

        return TableDTO(name = "ProjectSkill", columns = column, records = records)
    }

    fun getProjectList(): List<String> {
        val projects = projectRepository.findAll()

        return projects.map { "${it.id} (${it.name})" }.toList()
    }

    fun getSkillList(): List<String> {
        val projects = skillRepository.findAll()

        return projects.map { "${it.id} (${it.name})" }.toList()
    }
}