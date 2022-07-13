package com.corpgtb.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    public AppConfig() {
        super();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/csrf/**",
                        "/error/**",
                        "/v3/api-docs/**",
                        "/swagger.json",
                        "/resources/**",
                        "/auth/**",
                        "/test",
                        "/file/download/**",
                        "/public/**",
                        "/webui/**", "/configuration/**", "/swagger-ui/**", "/swagger-resources/**", "/api-docs",
                        "/api-docs/**", "/v2/api-docs/**", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js",
                        "/**/*.png", "/**/*.jpg", "/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf", "/**/*.woff",
                        "/**/*.otf");
    }
}