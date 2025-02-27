package com.seunghyun.portfolio.domain.repository

import com.seunghyun.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<Experience, Long>