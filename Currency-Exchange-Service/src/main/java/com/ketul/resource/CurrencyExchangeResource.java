package com.ketul.resource;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ketul.module.CurrencyExchange;
import com.ketul.module.dto.CurrencyExchangeDto;
import com.ketul.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeResource {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeResource.class);
	
	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/from/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
		
		logger.info("getCurrencyExchange is called {} to {}", from, to);
		return currencyExchangeService.getCurrencyExchange(from, to);
	}
	
	@PostMapping("/save")
	public CurrencyExchange saveCurrencyExchange(@RequestBody CurrencyExchangeDto currencyExchangeDto) {
		return currencyExchangeService.saveCurrencyExchange(mapper.map(currencyExchangeDto, CurrencyExchange.class));
	}
}
