package com.product.manager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.product.manager.domain.entity.Product;
import com.product.manager.service.ProductManagerService;

@RunWith(value = SpringRunner.class)
@WebMvcTest(value = ProductManagerController.class)
public class ProductManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private final String BASE_URI = "/products";

	@MockBean
	private ProductManagerService productService;

	@Test
	public void getAllAvailableProducts() throws Exception {
		List<Product> listOfProducts = listOfProducts();
		when(productService.getAllProducts()).thenReturn(listOfProducts);

		mockMvc.perform(get(BASE_URI + "/all")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}

	@Test
	public void updateProductBrandNameTest() throws Exception {
		when(productService.updateProductBrand(Mockito.anyLong(), Mockito.anyString()))
				.thenReturn(testProductBrandName());

		Map<String, String> brandName = new HashMap<>();
		brandName.put("brand", "TEST_NAME");
		mockMvc.perform(patch(BASE_URI + "/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(brandName.toString()))
				.andDo(print()).andExpect(status().isOk());
	}

	private List<Product> listOfProducts() {
		List<Product> products = new ArrayList<>();

		Product p1 = new Product();
		p1.setId(1L);
		p1.setBrand("Brand A");
		p1.setName("Product A");
		p1.setPrice(BigDecimal.valueOf(100.00));
		p1.setOnsale(true);
		products.add(p1);

		Product p2 = new Product();
		p2.setId(2L);
		p2.setBrand("Brand A");
		p2.setName("Product B");
		p2.setPrice(BigDecimal.valueOf(200.00));
		p2.setOnsale(false);
		products.add(p2);

		return products;
	}

	private Product testProductBrandName() {
		Product p1 = new Product();
		p1.setId(1L);
		p1.setBrand("TEST_NAME");
		p1.setName("Product A");
		p1.setPrice(BigDecimal.valueOf(100.00));
		p1.setOnsale(true);
		return p1;
	}
}
