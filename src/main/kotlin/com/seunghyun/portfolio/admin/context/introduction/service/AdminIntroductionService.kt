package com.seunghyun.portfolio.admin.context.introduction.service

import com.seunghyun.portfolio.admin.data.TableDTO
import com.seunghyun.portfolio.domain.entity.Introduction
import com.seunghyun.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service

@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository
) {
    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}