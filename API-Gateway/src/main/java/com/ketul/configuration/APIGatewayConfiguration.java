package com.ketul.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				
				.route(p -> p.path("/get") // This is a path which we want to map
						.filters(f -> f
								.addRequestHeader("MyHeader", "Ketul") // add request header
								.addRequestParameter("MyParam", "KetulPatel")) // add requestParam
						.uri("http://httpbin.org:80")) // This is URL
			
				.route(p -> p.path("/currency-exchange/**") // This is a path which we want to map
						.uri("lb://currency-exchange-service")) // This is service name which are register with eureka
				
				.route(p -> p.path("/currency-conversion/**") // This is a path which we want to map
						.uri("lb://currency-conversion-service")) // This is service name which are register with eureka
				
				.route(p -> p.path("/currency-conversion/feign/**") // This is a path which we want to map
						.uri("lb://currency-conversion-service")) // This is service name which are register with eureka
				
				.route(p -> p.path("/conversion/**") // This is a path which we want to map
						.filters(f -> f.rewritePath(
								"/conversion(?<segment>.*)",
								"/currency-conversion${segment}")) // replace paths
						.uri("lb://currency-conversion-service")) // This is service name which are register with eureka
				
				.build();
	}
}
