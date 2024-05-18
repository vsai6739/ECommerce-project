package com.product.ecommerce;

import com.product.ecommerce.Controllers.ProductController;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class sampleTest {
    @Autowired
    ProductController productController;
    @Test
    public void addition(){
        int i = 2;
        int j = i+2;
        //assert j==4;
        //assertEquals(3,j);
        //assertNotEquals(3,j);
        //assertTrue(3==3,"answer is correct");
        //assertThrows(ProductNotFoundException.class,()->productController.getProductById(10L));

    }
//    @Test
//    void testGetProductByIdProductNotFound() {
//        assertThrows(ProductNotFoundException.class,
//                () -> productController.getProductById(10000L));
//    }


}
