package com.ketul.resource;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ketul.module.CurrencyConversion;
import com.ketul.service.CurrencyConversionService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionResource {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyConversionResource.class);
	
	@Autowired
	private CurrencyConversionService currencyConversionService;

	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
//	@Retry(name = "currency-convert", fallbackMethod = "convertCurrencyFallBack")
	@CircuitBreaker(name = "currency-convert", fallbackMethod = "convertCurrencyFallBack")
	@RateLimiter(name = "currency-convert")
	@Bulkhead(name = "currency-convert")
	public CurrencyConversion convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		logger.info("orignal method");
		return currencyConversionService.convertCurrency(from, to, quantity);
	}
	
	@GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		return currencyConversionService.convertCurrencyFeign(from, to, quantity);
	}
	
	public CurrencyConversion convertCurrencyFallBack(Exception e) {
		logger.info("FallBack method");
		return new CurrencyConversion();
	}
}
