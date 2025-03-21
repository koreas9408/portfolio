package com.seunghyun.portfolio.admin.context.project.controller

import com.seunghyun.portfolio.admin.context.project.form.ProjectForm
import com.seunghyun.portfolio.admin.context.project.service.AdminProjectService
import com.seunghyun.portfolio.admin.data.ApiResponse
import com.seunghyun.portfolio.admin.data.TableDTO
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/project")
class AdminProjectApiController(
    private val projectService: AdminProjectService
) {

    @PostMapping
    fun postProject(@RequestBody @Validated form: ProjectForm): ResponseEntity<Any> {
        projectService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putProject(@PathVariable id: Long, @RequestBody @Validated form: ProjectForm): ResponseEntity<Any> {
        projectService.update(id, form)

        return ApiResponse.successUpdate()
    }

    @GetMapping("/{id}/details")
    fun getProjectDetails(@PathVariable id: Long): TableDTO {
        return projectService.getProjectDetailTable(id)
    }

}