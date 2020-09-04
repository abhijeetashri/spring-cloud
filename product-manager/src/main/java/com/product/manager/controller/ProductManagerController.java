package com.product.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.manager.domain.ProductDTO;
import com.product.manager.domain.entity.Product;
import com.product.manager.service.ProductManagerService;

@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductManagerController {

	@Autowired
	private ProductManagerService productService;

	@GetMapping(path = "/all")
	public List<Product> findAll() {
		return this.productService.getAllProducts();
	}

	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(@PathVariable(name = "id") Long id, @RequestBody Map<String, String> brandName) {
		return this.productService.updateProductBrand(id, brandName.get("brand"));
	}

	@GetMapping(path = "/brands")
	public Map<String, List<ProductDTO>> filterByBrands() {
		return this.productService.filteredBrands();
	}
}
