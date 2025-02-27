package com.seunghyun.portfolio.domain.repository

import com.seunghyun.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long>