package com.product.manager.service;

import java.util.List;
import java.util.Map;

import com.product.manager.domain.ProductDTO;
import com.product.manager.domain.entity.Product;

public interface ProductManagerService {

	List<Product> getAllProducts();

	Product updateProductBrand(Long id, String brandName);
	
	Map<String, List<ProductDTO>> filteredBrands();
}
