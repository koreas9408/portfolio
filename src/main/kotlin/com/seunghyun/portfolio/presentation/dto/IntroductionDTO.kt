package com.seunghyun.portfolio.presentation.dto

import com.seunghyun.portfolio.domain.entity.Introduction

data class IntroductionDTO(
    val content: String
) {
    // 생성자 추가
    constructor(introduction: Introduction) : this(
        content = introduction.content
    )
}