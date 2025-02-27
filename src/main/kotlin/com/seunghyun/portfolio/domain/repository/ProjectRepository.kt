package com.seunghyun.portfolio.domain.repository

import com.seunghyun.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long>