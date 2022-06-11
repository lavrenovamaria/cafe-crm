package com.comname.cafecrm.domain.converter

import com.comname.cafecrm.domain.dto.IdDto

fun Long.toDto() = IdDto(this)