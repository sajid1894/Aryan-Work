package com.java.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.app.dto.ColorSwatchDto;
import com.java.app.dto.ProductDto;
import com.java.app.entity.Price;
import com.java.app.entity.Product;
import com.java.app.repository.PriceReductionRepository;
import com.java.app.utility.ColorUtil;
import com.java.app.utility.CurrencyUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PriceReductionServiceImpl implements PriceReductionService {

	@Autowired
	PriceReductionRepository repo;

	@Autowired
	ColorUtil colorUtil;
	@Autowired
	CurrencyUtil currencyUtil;

	@Override
	public List<ProductDto> fetchAllProducts(String priceLabel) {
		
		log.info("Inside PriceReductionServiceImpl Class....");
		log.info("Calling fetchAllProducts("+priceLabel+") method....");
		
		log.info("Fetching Products from Repository....");
		List<Product> fetchedProducts = repo.fetchAllProducts();
		log.info("Fetched Done from Repository....");
		
		log.info("Processing the fetched Products....");
		List<ProductDto> productDtoList = fetchedProducts.stream()
					.filter(pdt->!(pdt==null))
					.map(pdt->{
						return processProductEntity(pdt, priceLabel);
					}).collect(Collectors.toList());
		log.info("Processing Done....");
		
		log.info("Sorting the fetched Products based on reduced price....");
		productDtoList.sort((ProductDto p1, ProductDto p2)->p2.getPriceReduced().compareTo(p1.getPriceReduced()));
		log.info("Sorting Done....");
		
		log.info("Sending Processed Products (DTO) to the controller....");
		return productDtoList;
	}

	private ProductDto processProductEntity(Product pdt, String priceLabel) {
		ProductDto productDto = new ProductDto();
		if(pdt!=null) {

			List<ColorSwatchDto> colorSwatchList = new ArrayList<>();
			if(pdt.getColorSwatches().size()>0) {
				colorSwatchList = pdt.getColorSwatches().stream()
						.map(eachColorSwatch->{
							return new ColorSwatchDto(
									eachColorSwatch.getBasicColor(), 
									colorUtil.basicToRgb(eachColorSwatch.getBasicColor()), 
									eachColorSwatch.getSkuId());
						}).collect(Collectors.toList());
			}
			
			Price price = Optional.ofNullable(pdt.getPrice()).orElse(new Price());
			
			String currency = Optional.ofNullable(price.getCurrency()).orElse("");
			String currencyCode = currencyUtil.isoCodeToSymbol(currency);
			
			String fetchedNowPriceStr = (String) Optional.ofNullable(price.getNow()).orElse("");
			String nowPriceStr=currencyUtil.extractPriceFormat(fetchedNowPriceStr);
			
			String fetchedWasPriceStr = Optional.ofNullable(price.getWas()).orElse("");
			String wasPriceStr=currencyUtil.extractPriceFormat(fetchedWasPriceStr);
			
			Double reducedPrice = Double.parseDouble(wasPriceStr)-Double.parseDouble(nowPriceStr);
			
			String proccessedPriceLabel = "Was " + currencyCode + wasPriceStr 
											+ ", now " + currencyCode + nowPriceStr;
			switch(priceLabel.toLowerCase()) {
			case "showwasthennow":
				String thenPrice = "";
				String fetchedThen2PriceStr = Optional.ofNullable(price.getThen2()).orElse("");
				if(!fetchedThen2PriceStr.equals("")) {
					thenPrice=currencyUtil.extractPriceFormat(fetchedThen2PriceStr);
				}else {
					String fetchedThen1PriceStr = Optional.ofNullable(price.getThen1()).orElse("");
					thenPrice=currencyUtil.extractPriceFormat(fetchedThen1PriceStr);
				}
				proccessedPriceLabel = "Was " + currencyCode + wasPriceStr
										+ ", then " + currencyCode + thenPrice
										+ ", now " + currencyCode + nowPriceStr;
				break;
			case "showpercdscount":
				String discountStr = Math.round(100*reducedPrice/Double.parseDouble(wasPriceStr)) + "%";
				proccessedPriceLabel = discountStr + " off - " + "now " + currencyCode + nowPriceStr;
				break;
			}
			
			productDto.setProductId(pdt.getProductId());
			productDto.setTitle(pdt.getTitle());
			productDto.setColorSwatches(colorSwatchList);
			productDto.setNowPrice(currencyCode + nowPriceStr);
			productDto.setPriceLabel(proccessedPriceLabel);
			productDto.setPriceReduced(reducedPrice);
		}
		return productDto;
	}

}
