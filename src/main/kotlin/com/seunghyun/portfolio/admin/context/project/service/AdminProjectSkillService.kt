package com.seunghyun.portfolio.admin.context.project.service

import com.seunghyun.portfolio.admin.context.project.form.ProjectSkillForm
import com.seunghyun.portfolio.admin.data.TableDTO
import com.seunghyun.portfolio.admin.exception.AdminBadRequestException
import com.seunghyun.portfolio.admin.exception.AdminInternalServerErrorException
import com.seunghyun.portfolio.domain.entity.ProjectSkill
import com.seunghyun.portfolio.domain.repository.ProjectRepository
import com.seunghyun.portfolio.domain.repository.ProjectSkillRepository
import com.seunghyun.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminProjectSkillService(
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository
    private val projectSkillRepository: ProjectSkillRepository
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

    @Transactional
    fun save(form: ProjectSkillForm) {
        // format - id (name)
        val projectId = parseId(form.project)
        val skillId = parseId(form.skill)
        projectSkillRepository.findByProjectIdAndSkillId(projectId, skillId)
            .ifPresent { throw AdminBadRequestException("이미 매핑된 데이터 입니다.") }

        val project = projectRepository.findById(projectId)
            .orElseThrow { throw AdminBadRequestException("ID ${projectId}에 해당하는 데이터를 찾을 수 없습니다.") }
        val skill = skillRepository.findById(skillId)
            .orElseThrow { throw AdminBadRequestException("ID ${skillId}에 해당하는 데이터를 찾을 수 없습니다.") }
        val projectSkill = ProjectSkill(
            project = project,
            skill = skill
        )

        // JPA에서 스냅샷을 비교해서 Update 처리
        project.skills.add(projectSkill)
    }

    @Transactional
    fun delete(id: Long) {
        projectRepository.deleteById(id)
    }

    private fun parseId(line: String): Long {
        try {
            val endIndex = line.indexOf(" ") - 1
            val id = line.slice(0..endIndex).toLong()

            return id
        } catch (e: Exception) {
            throw AdminInternalServerErrorException("ID 추출 중 오류가 발생하였습니다.")
        }
    }
}