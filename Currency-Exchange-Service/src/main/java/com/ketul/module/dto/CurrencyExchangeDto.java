package com.ketul.module.dto;

import java.math.BigDecimal;

public class CurrencyExchangeDto {

	private String from;
	private String to;
	private BigDecimal conversionMultiple;

	public CurrencyExchangeDto() {
		super();
	}

	public CurrencyExchangeDto(String from, String to, BigDecimal conversionMultiple) {
		super();
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}

	public String getfrom() {
		return from;
	}

	public void setfrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	@Override
	public String toString() {
		return "CurrencyExchangeDto [from=" + from + ", to=" + to + ", conversionMultiple=" + conversionMultiple + "]";
	}

}
