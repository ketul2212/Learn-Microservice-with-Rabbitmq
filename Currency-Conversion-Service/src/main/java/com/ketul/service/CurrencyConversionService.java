package com.ketul.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ketul.module.CurrencyConversion;
import com.ketul.service.feign.FeignClientService;

@Service
public class CurrencyConversionService {

	@Autowired
	private FeignClientService feignClientService;
	
	@Autowired
	private RestTemplate restTemplate;

	public CurrencyConversion convertCurrency(String from, String to, BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

		CurrencyConversion currencyConversion = responseEntity.getBody();

		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getPort());
	}

	public CurrencyConversion convertCurrencyFeign(String from, String to, BigDecimal quantity) {

		CurrencyConversion currencyConversion = feignClientService.getCurrencyExchange(from, to);

		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getPort());
	}
}
