package com.seunghyun.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Achievement(
    title: String,
    description: String,
    achievedDate: LocalDate?,
    host: String,
    isActive: Boolean,
) : BaseEntity() {

    /*
        TABLE: ID만을 다루는 테이블을 생성
        SEQUENCE: 데이터베이스에서 제공하는 시퀀스 기능을 사용
        IDENTITY: 데이터베이스 위임
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null

    var title: String = title

    var description: String = description

    var achievedDate: LocalDate? = achievedDate

    var host: String = host

    var isActive: Boolean = isActive

}