package com.ketul.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ketul.module.CurrencyExchange;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Integer> {

	public CurrencyExchange findByFromAndTo(String from, String to);
}
