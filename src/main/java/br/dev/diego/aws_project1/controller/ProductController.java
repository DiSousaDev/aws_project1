package br.dev.diego.aws_project1.controller;

import br.dev.diego.aws_project1.entities.dtos.ProductDTO;
import br.dev.diego.aws_project1.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    private static final String FORMAT_LOGGER = ">>> {}:{}";
    private static final String CLASS_NAME = ProductController.class.getSimpleName();

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        LOG.info(FORMAT_LOGGER, CLASS_NAME, Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
        LOG.info(FORMAT_LOGGER, CLASS_NAME, Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(value="/bycode")
    public ResponseEntity<ProductDTO> findProductByCode(@RequestParam String code) {
        LOG.info(FORMAT_LOGGER, CLASS_NAME, Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insertNewProduct(@RequestBody ProductDTO productDTO) {
        LOG.info(FORMAT_LOGGER, CLASS_NAME, Thread.currentThread().getStackTrace()[1].getMethodName());
        ProductDTO obj = service.insertNewProduct(productDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        LOG.info(FORMAT_LOGGER, CLASS_NAME, Thread.currentThread().getStackTrace()[1].getMethodName());
        ProductDTO obj = service.updateProduct(id, productDTO);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        LOG.info(FORMAT_LOGGER, CLASS_NAME, Thread.currentThread().getStackTrace()[1].getMethodName());
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
