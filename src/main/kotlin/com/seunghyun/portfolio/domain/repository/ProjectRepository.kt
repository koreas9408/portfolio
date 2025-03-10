package com.seunghyun.portfolio.domain.repository

import com.seunghyun.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long> {
    // 이대로 사용하면 성능에 굉장히 치명적이다. (개선 필요!)
    fun findAllByIsActive(isActive: Boolean): List<Project>
}