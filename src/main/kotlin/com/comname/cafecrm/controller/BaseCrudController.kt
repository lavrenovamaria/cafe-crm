package com.comname.cafecrm.controller

import com.comname.cafecrm.domain.dto.BaseDto
import com.comname.cafecrm.domain.dto.IdDto
import com.comname.cafecrm.domain.model.BaseModel
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

interface BaseCrudController<Dto : BaseDto<out BaseModel>> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create empty entity")
    fun create(): IdDto

    @GetMapping("/{id}")
    @Operation(summary = "Get entity by ID")
    fun get(@PathVariable("id") id: Long): Dto

    @PatchMapping("/{id}")
    @Operation(summary = "Update entity")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody dto: Dto
    ): Dto

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete entity by ID")
    fun delete(@PathVariable("id") id: Long)

}