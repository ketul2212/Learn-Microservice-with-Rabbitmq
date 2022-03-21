package com.ketul.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class APIGatewayGlobalConfiguration implements GlobalFilter{
	
	private long numberOfCommingRequest = 0;
	
	private Logger logger = LoggerFactory.getLogger(APIGatewayGlobalConfiguration.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("");
		logger.info("hitted Path is -> {} and number of request is {}", exchange.getRequest().getPath(), ++numberOfCommingRequest);
		return chain.filter(exchange); // for execution continue
	}

}
