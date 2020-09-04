package com.product.manager.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.manager.domain.ProductDTO;
import com.product.manager.domain.entity.Product;
import com.product.manager.domain.repository.ProductRepository;

@RunWith(value = SpringRunner.class)
public class ProductManagerServiceImplTest {

	@InjectMocks
	private ProductManagerServiceImpl productService;

	@Mock
	private ProductRepository productsRepo;

	@Mock
	private ModelMapper modelMapper;

	private static final Long PRODUCT_ID = 1L;

	private static final String BRAND_NAME = "TEST_NAME";

	@Test
	public void getAllProductsTest() {
		List<Product> products = Collections.singletonList(mock(Product.class));

		when(productsRepo.findAll()).thenReturn(products);

		List<Product> returnedProducts = productService.getAllProducts();

		assertEquals("Size is not same", products, returnedProducts);
	}

	@Test
	public void updateProductBrandTest() {
		Product productEntity = new Product();
		when(productsRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(productEntity));
		when(productsRepo.save(Mockito.any(Product.class))).thenReturn(productEntity);

		Product savedEntity = productService.updateProductBrand(PRODUCT_ID, BRAND_NAME);

		assertEquals("Brand Name not updated!", BRAND_NAME, savedEntity.getBrand());
	}

	@Test
	public void filteredBrands() {
		List<Product> products = this.productsRepo.findAll(sortByPriceAndBrand());
		Map<String, List<ProductDTO>> mapOfProducts = new TreeMap<>();
		products.stream().forEach(product -> {
			String brand = product.getBrand();
			mapOfProducts.computeIfAbsent(brand, k -> new ArrayList<>());
			List<ProductDTO> listOfProducts = mapOfProducts.get(brand);
			listOfProducts.add(getDtoFrom(product));
			mapOfProducts.put(brand, listOfProducts);
		});
	}

	private Sort sortByPriceAndBrand() {
		return new Sort(Direction.ASC, "brand").and(new Sort(Direction.ASC, "price"));
	}

	private ProductDTO getDtoFrom(Product o) {
		return modelMapper.map(o, ProductDTO.class);
	}
}
