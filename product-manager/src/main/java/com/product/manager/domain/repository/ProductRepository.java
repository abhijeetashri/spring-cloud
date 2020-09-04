package com.product.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.manager.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
