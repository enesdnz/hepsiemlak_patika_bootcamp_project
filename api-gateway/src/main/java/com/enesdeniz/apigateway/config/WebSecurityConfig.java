package com.enesdeniz.apigateway.config;

import com.enesdeniz.apigateway.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class WebSecurityConfig{

	@Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		//// @formatter:off
		http
		.httpBasic().disable()
		.formLogin().disable()
		.csrf().disable();

		return http.build();
		// @formatter:on

	}

	@Autowired
	private JwtFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
		//// @formatter:off


				.route("auth-service",
						r -> r.path("/auth")
							  .uri("http://localhost:5863"))
				.route("common-service",
						r -> r.method(HttpMethod.POST)
						.and()
						.path(("/advert/**"))
						.filters(f -> f.filter(filter)).uri("http://localhost:5861"))
				.route("common-service",
						r -> r.method(HttpMethod.GET)
						.and()
						.path("/user/**")
						.filters(f -> f.filter(filter)).uri("http://localhost:5861"))
                .route("common-service",
                    r -> r.method(HttpMethod.GET)
                        .and()
                        .path("/product/**")
                        .filters(f -> f.filter(filter)).uri("http://localhost:5861"))
                .route("payment-service",
                    r -> r.method(HttpMethod.GET)
                        .and()
                        .path("/payments/**")
                        .filters(f -> f.filter(filter)).uri("http://localhost:5864"))
                    .build();

		// @formatter:on
	}

}
