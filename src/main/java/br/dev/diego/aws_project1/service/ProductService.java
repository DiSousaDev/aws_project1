package br.dev.diego.aws_project1.service;

import br.dev.diego.aws_project1.entities.Product;
import br.dev.diego.aws_project1.entities.dtos.ProductDTO;
import br.dev.diego.aws_project1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return new ProductDTO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found")));
    }

    @Transactional(readOnly = true)
    public ProductDTO findByCode(String code) {
        return new ProductDTO(repository.findByCode(code).orElseThrow(() -> new EntityNotFoundException("Product not found")));
    }

    @Transactional
    public ProductDTO insertNewProduct(ProductDTO productDTO) {
        return new ProductDTO(repository.save(getProductFromProductDTO(productDTO)));
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        repository.getReferenceById(id);
        Product produto = getProductFromProductDTO(productDTO);
        produto.setId(id);
        return new ProductDTO(repository.save(produto));
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Product getProductFromProductDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setModel(productDTO.getModel());
        product.setCode(productDTO.getCode());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
