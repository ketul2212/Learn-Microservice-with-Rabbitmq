package com.ketul.service.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ketul.module.CurrencyConversion;

/*
 * If we fix the url then it will take this url to call another mocroservices and 
 * if we remove url then it will use loadbalance.
 * 
 * @org.springframework.cloud.openfeign.FeignClient(name = "Currency-Exchange-Service", url = "localhost:8000")
*/
@org.springframework.cloud.openfeign.FeignClient(name = "currency-exchange-service")
public interface FeignClientService {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion getCurrencyExchange(@PathVariable String from, @PathVariable String to);
}
