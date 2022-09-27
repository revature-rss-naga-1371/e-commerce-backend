package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.UserDto;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import com.revature.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {

        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Authorized
    @GetMapping
    public ResponseEntity<List<Product>> getInventory() {
        return ResponseEntity.ok(productService.findAll());
    }


//    @PostMapping("/create-item")
//    public void resetPW(
//            @RequestBody Product product
//    ) {
//        productService.createItem(product);
//    }
//
//    @PatchMapping("/update-item/{id}")
//    public void updateItem(@PathVariable("id") int id,
//                           @RequestBody Product product) {
//        productService.updateItem(id, product);
//    }

    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Optional<Product> optional = productService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @Authorized
    @PutMapping
    public ResponseEntity<Product> upsert(@RequestBody Product product) {
        System.out.println(product);
        if (product.getId() != 0) {
            System.out.println("here");
            Optional<Product> currentProduct = productRepository.findById(product.getId());
            if (product.getImage() == null) {
                System.out.println(currentProduct.get().getImage());
                product.setImage(currentProduct.get().getImage());
                return ResponseEntity.ok(productService.save(product));
            }
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @Authorized
    @PatchMapping
    public ResponseEntity<List<Product>> purchase(@RequestBody List<ProductInfo> metadata) { 	
    	List<Product> productList = new ArrayList<Product>();
    	
    	for (int i = 0; i < metadata.size(); i++) {
    		Optional<Product> optional = productService.findById(metadata.get(i).getId());

    		if(!optional.isPresent()) {
    			return ResponseEntity.notFound().build();
    		}

    		Product product = optional.get();

    		if(product.getQuantity() - metadata.get(i).getQuantity() < 0) {
    			return ResponseEntity.badRequest().build();
    		}
    		
    		product.setQuantity(product.getQuantity() - metadata.get(i).getQuantity());
    		productList.add(product);
    	}
        
        productService.saveAll(productList, metadata);

        return ResponseEntity.ok(productList);
    }

    @Authorized
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
        Optional<Product> optional = productService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);

        return ResponseEntity.ok(optional.get());
    }
}
