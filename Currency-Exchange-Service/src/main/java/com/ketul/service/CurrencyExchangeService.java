package com.ketul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ketul.module.CurrencyExchange;
import com.ketul.repo.CurrencyExchangeRepo;

@Service
public class CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRepo currencyExchangeRepo;
	
	@Autowired
	private Environment environment;
	
	public CurrencyExchange saveCurrencyExchange(CurrencyExchange currencyExchange) {
		currencyExchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return currencyExchangeRepo.save(currencyExchange);
	}
	
	public CurrencyExchange getCurrencyExchange(String form, String to) {
		CurrencyExchange currencyExchange = currencyExchangeRepo.findByFromAndTo(form, to);
		
		if(currencyExchange == null)
			return new CurrencyExchange();
		
		currencyExchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return currencyExchange;
	}
}
