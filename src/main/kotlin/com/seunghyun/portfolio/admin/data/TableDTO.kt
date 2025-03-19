package com.seunghyun.portfolio.admin.data

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

// Grid에 뿌려줄 데이터
class TableDTO(
    val name: String,
    val columns: List<String>,
    val records: List<List<String>>
) {
    companion object {
        fun <T : Any> from(classInfo: KClass<T>, entities: List<Any>, vararg filterings: String): TableDTO {
            val name = classInfo.simpleName ?: "Unknown"
            val columns = createColumns(classInfo, *filterings) // filterings : 제외하고 싶은 클래스 이름
            val records = entities.map { entity ->
                columns.map { column ->
                    classInfo.memberProperties
                        .find { column == it.name }
                        ?.getter
                        ?.call(entity)
                        .toString()
                }.toList()
            }.toList()

            return TableDTO(name = name, columns = columns, records = records)
        }

        private fun <T : Any> createColumns(classInfo: KClass<T>, vararg filterings: String): MutableList<String> {
            val mainColumns = classInfo.java.declaredFields
                .filter { !filterings.contains(it.name) }
                .map { it.name }
                .toMutableList() // 동적으로 리스트를 수정해야 함

            val baseColumns = classInfo.java.superclass.declaredFields
                .map { it.name }
                .toMutableList()

            return (mainColumns + baseColumns).toMutableList()
        }
    }
}