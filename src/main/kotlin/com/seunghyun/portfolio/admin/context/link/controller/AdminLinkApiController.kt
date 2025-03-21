package com.seunghyun.portfolio.admin.context.link.controller

import com.seunghyun.portfolio.admin.context.link.form.LinkForm
import com.seunghyun.portfolio.admin.context.link.service.AdminLinkService
import com.seunghyun.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/link")
class AdminLinkApiController(
    private val linkService: AdminLinkService
) {

    @PostMapping
    fun postLink(@RequestBody @Validated form: LinkForm): ResponseEntity<Any> {
        linkService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putLink(@PathVariable id: Long, @RequestBody @Validated form: LinkForm): ResponseEntity<Any> {
        linkService.update(id, form)

        return ApiResponse.successUpdate()
    }


}