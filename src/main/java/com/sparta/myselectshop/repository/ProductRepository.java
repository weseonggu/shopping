package com.sparta.myselectshop.repository;

import com.sparta.myselectshop.entity.Product;
import org.hibernate.type.descriptor.converter.internal.JpaAttributeConverterImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
