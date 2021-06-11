package com.java.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.app.dto.ProductDto;
import com.java.app.service.PriceReductionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController("/api")
@Slf4j
public class PriceReductionController {

	@Autowired
	PriceReductionService service;

	@Operation(summary = "Get the array of products, only contain products with a price reduction and sorted to show\r\n"
			+ "the highest price reduction first.")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Fetched all Products, sorted by reduced prices", 
					content = {
							@Content(mediaType = "application/json", 
									array = @ArraySchema(schema = @Schema( implementation = ProductDto.class))
							) 
					}
			)
	})
	@GetMapping("/fetchProducts")
	public ResponseEntity<Object> getProducts(
			@Parameter(
					name="labelType",
					required = false, 
					example = "ShowWasNow",
					description = "Optional query param to change priceLabel",
					schema = @Schema(description = "Optional query param to change priceLabel",type = "string", allowableValues = {"ShowWasNow", "ShowWasThenNow", "ShowPercDscount"})) 
			@RequestParam(name = "labelType", required = false) Optional<String> priceLabelOp,
			HttpServletRequest request){
		log.info("Request Recielved from client: "+request.getRequestURL());

		log.info("Inside PriceReductionController Class....");
		log.info("Calling getProducts() method....");
		try {
			log.info("Fetching Products from Service....");
			List<ProductDto> productList = service.fetchAllProducts(priceLabelOp.orElse("ShowWasNow"));
			log.info("Fetched Done from Service....");

			log.info("Returing the response to the client....");
			return new ResponseEntity<>(productList, HttpStatus.OK);
		}catch (Exception e) {
			log.info("Error!!! Fetching Products from Service....");
			e.printStackTrace();
			Map<String, String> errorMap = new HashMap<>();
			return new ResponseEntity<>(errorMap.put("errorMessage", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
