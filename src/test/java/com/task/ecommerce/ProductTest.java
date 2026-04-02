package com.task.ecommerce;

import com.task.ecommerce.entity.Product;
import com.task.ecommerce.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testSaveProduct() {

        // create product
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(50000);
        product.setStockQuantity(10);

        // mock repository
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(productRepository.save(product)).thenReturn(product);

        // call save
        Product saved = productRepository.save(product);

        // check result
        assertEquals("Laptop", saved.getName());
        assertEquals(50000, saved.getPrice());
    }
}