package com.seunghyun.portfolio.presentation.dto

// data class는 DTO 목적으로 사용하기 좋다.
// toString() : key/value 형태로 만들어준다.
data class AchievementDTO(
    var title: String,
    var description: String,
    var host: String,
    var achievedDate: String?
)