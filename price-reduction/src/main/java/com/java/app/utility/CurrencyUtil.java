package com.java.app.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class CurrencyUtil {
	
	private static final Map<String, String> CURRENCY_TABLE = new HashMap<>();
	
	static {
		CURRENCY_TABLE.put("EUR","€");
		CURRENCY_TABLE.put("GBP","£");
		CURRENCY_TABLE.put("USD","$");
		CURRENCY_TABLE.put("JPY","¥");
	}
	
	public String isoCodeToSymbol(String code) {
		return code.length()==3?Optional.ofNullable(CURRENCY_TABLE.get(code.toUpperCase())).orElse(""):code;
	}
	
	public String extractPriceFormat(String fetchedPriceStr) {
		String priceStr="0.00";
		if(!fetchedPriceStr.equals("")) {
			Double price = Double.parseDouble(fetchedPriceStr);
			if(price<10)
				priceStr = price.toString();
			else
				priceStr = price.intValue()+"";
		}
		return priceStr;
	}
}
