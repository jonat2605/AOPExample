package com.bpop.kaisen.services;

import com.bpop.kaisen.models.dto.DataInfoRes;
import com.bpop.kaisen.models.entities.Product;
import com.bpop.kaisen.repositories.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public DataInfoRes createAndUpdateProduct(Product product) {
        try {
            Product productSearch = productRepository.findById(product.getId()).orElseGet(() -> null);
            if (productSearch == null) {
                productRepository.save(product);
                return DataInfoRes.builder().statusCode(201).responseStatus("Producto creado satisfactoriamente").build();
            } else {
                productSearch = Product.builder().id(productSearch.getId()).name(product.getName()).price(product.getPrice()).quantity(product.getQuantity()).build();
                productRepository.save(productSearch);
                return DataInfoRes.builder().statusCode(201).responseStatus("Producto actualizado satisfactoriamente").build();
            }
        } catch (Exception e) {
            log.info("Error al intentar actualizar o crear " + e);
            return DataInfoRes.builder().statusCode(500).responseStatus("No se pudo crear o actualizar el producto").build();
        }
    }

    public DataInfoRes getAllProducts() {
        try {
            return DataInfoRes.builder().statusCode(200).responseStatus("Consulta exitosa").dataInfo(Collections.singletonList(productRepository.findAll())).build();
        } catch (Exception e) {
            log.info("Error al intentar actualizar o crear " + e);
            return DataInfoRes.builder().statusCode(500).dataInfo(Collections.emptyList()).responseStatus("No se pudo realizar la consulta").build();
        }
    }

    public DataInfoRes getOrDeleteProductById(Integer id, String action) {
        try {
                if (action.equalsIgnoreCase("GET")) {
                    List<Product> products = new ArrayList<>();
                    Product product = productRepository.findById(id).orElseGet(() -> null);
                    products.add(product);
                    return DataInfoRes.builder().statusCode(200).responseStatus("Consulta exitosa").dataInfo(Collections.singletonList(products)).build();
                } else {
                    productRepository.deleteById(id);
                    return DataInfoRes.builder().statusCode(200).responseStatus("Producto eliminado exitosamente").build();
                }
        } catch (Exception e) {
            log.info("Error al intentar actualizar o crear " + e);
            return DataInfoRes.builder().statusCode(500).responseStatus("No se pudo eliminar o consultar el producto").build();
        }
    }

}
