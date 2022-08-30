package com.pfd.dia.api.auth.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val tokenValidateInterceptor: TokenValidateInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenValidateInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/health-check")
    }
}