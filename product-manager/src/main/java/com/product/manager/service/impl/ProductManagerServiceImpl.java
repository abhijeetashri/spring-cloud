package com.product.manager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.product.manager.domain.ProductDTO;
import com.product.manager.domain.entity.Product;
import com.product.manager.domain.repository.ProductRepository;
import com.product.manager.service.ProductManagerService;

@Service
public class ProductManagerServiceImpl implements ProductManagerService {

	@Autowired
	private ProductRepository productsRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Product> getAllProducts() {
		return this.productsRepo.findAll();
	}

	@Override
	public Product updateProductBrand(Long id, String brandName) {
		if (!StringUtils.isEmpty(brandName)) {
			Optional<Product> productEntity = this.productsRepo.findById(id);
			if (productEntity.isPresent()) {
				Product toBeUpdated = productEntity.get();
				toBeUpdated.setBrand(brandName);
				return this.productsRepo.save(toBeUpdated);
			}
		}
		return null;
	}

	@Override
	public Map<String, List<ProductDTO>> filteredBrands() {
		List<Product> products = this.productsRepo.findAll(sortByPriceAndBrand());
		Map<String, List<ProductDTO>> mapOfProducts = new TreeMap<>();
		products.stream().forEach(product -> {
			String brand = product.getBrand();
			mapOfProducts.computeIfAbsent(brand, k -> new ArrayList<>());
			List<ProductDTO> listOfProducts = mapOfProducts.get(brand);
			listOfProducts.add(getDtoFrom(product));
			mapOfProducts.put(brand, listOfProducts);
		});
		return mapOfProducts;
	}

	private Sort sortByPriceAndBrand() {
		return new Sort(Direction.ASC, "brand").and(new Sort(Direction.ASC, "price"));
	}

	private ProductDTO getDtoFrom(Product o) {
		return modelMapper.map(o, ProductDTO.class);
	}
}
