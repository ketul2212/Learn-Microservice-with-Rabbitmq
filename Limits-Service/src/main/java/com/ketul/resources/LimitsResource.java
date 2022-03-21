package com.ketul.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ketul.configuration.LimitsConfiguration;
import com.ketul.module.Limits;

@RestController
public class LimitsResource {

	@Autowired
	private LimitsConfiguration limitsConfiguration;
	
	@GetMapping("/limits")
	public Limits getLimit() {
		return new Limits(limitsConfiguration.getMinimum(), limitsConfiguration.getMaximum())
;//		return new Limits(1, 1000);
	}
}
