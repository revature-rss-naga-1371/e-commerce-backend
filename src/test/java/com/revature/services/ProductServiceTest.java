package com.revature.services;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository productRepositoryMock;

    private ProductService productService;

    @BeforeEach
    public void init() {
        productRepositoryMock = mock(ProductRepository.class);
        when(productRepositoryMock.findAll())
                .thenReturn(List.of(new Product(1, 5, 19.99, "shoes for a dog", "https://cdn.thewirecutter.com/wp-content/uploads/2018/11/dogboots-lowres-9867.jpg", "Dog shoes"),
                        new Product(2, 10, 14.30, "look very nice", "https://images.heb.com/is/image/HEBGrocery/001789063", "hair gel")));
        when(productRepositoryMock.findById(1))
                .thenReturn(Optional.of(new Product(1, 5, 19.99, "shoes for a dog", "https://cdn.thewirecutter.com/wp-content/uploads/2018/11/dogboots-lowres-9867.jpg", "Dog shoes")));
        productService = new ProductService(productRepositoryMock);
    }

    @Test
    public void findAllTest() {
        List<Product> products = productService.findAll();
        assertEquals(2, products.size());
    }

    @Test
    public void findByIdTest() {
        Optional<Product> product = productService.findById(1);
        assertThat(productService.findById(1).get(), equalTo(new Product(1, 5, 19.99, "shoes for a dog", "https://cdn.thewirecutter.com/wp-content/uploads/2018/11/dogboots-lowres-9867.jpg", "Dog shoes")));
    }

    @Test
    public void saveTest() {
        Product product = new Product(3, 20, 5.99, "best backpack in town", "https://images.jansport.com/is/image/JanSportBrand/jsp-pdp-right-pack-feature-2?$espot$", "Backpack");
        productService.save(product);
        verify(productRepositoryMock).save(product);
    }

    @Test
    public void saveAllTest() {
        List<Product> products = List.of(new Product(3, 7, 56.68, "mouse for a gamer", "https://thermaltake.azureedge.net/pub/media/catalog/product/cache/6bf0ed99c663954fafc930039201ed07/l/2/l20m01.jpg", "Gaming mouse"),
                new Product(4, 13, 25.43, "the best laptop on the market", "https://cdn.britannica.com/77/170477-050-1C747EE3/Laptop-computer.jpg", "laptop"));
        productService.saveAll(products, List.of(new ProductInfo(3, 3), new ProductInfo(4, 6)));
        verify(productRepositoryMock).saveAll(products);
    }


}
