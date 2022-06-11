package com.comname.cafecrm.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {

    companion object {
        const val TITLE = "CafeCRM API"
        const val VERSION = "1"
    }

    @Bean
    fun openApi(): OpenAPI = OpenAPI().info(Info().title(TITLE).version(VERSION))

}
