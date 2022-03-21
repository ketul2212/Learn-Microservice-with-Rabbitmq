package com.ketul.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class LimitsConfiguration {

	private int minimum;
	private int maximum;

	public LimitsConfiguration() {
		super();
	}

	public LimitsConfiguration(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	@Override
	public String toString() {
		return "Limits [minimum=" + minimum + ", maximum=" + maximum + "]";
	}
}
