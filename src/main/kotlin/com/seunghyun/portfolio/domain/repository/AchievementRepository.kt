package com.seunghyun.portfolio.domain.repository

import com.seunghyun.portfolio.domain.entity.Achievement
import org.springframework.data.jpa.repository.JpaRepository

interface AchievementRepository : JpaRepository<Achievement, Long> {
    // select * from achievements where is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Achievement>
}