package com.seunghyun.portfolio.presentation.dto

import com.seunghyun.portfolio.domain.entity.ProjectDetail

data class ProjectDetailDTO(
    val content: String,
    val url: String?
) {
    constructor(project: ProjectDetail) : this(
        content = project.content,
        url = project.url
    )
}