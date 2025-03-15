package com.seunghyun.portfolio.admin.data

// Grid에 뿌려줄 데이터
class TableDTO(
    val name: String,
    val columns: List<String>,
    val records: List<List<String>>
) {
}