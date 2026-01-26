package com.coursejava.webservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coursejava.webservice.entities.Category;
import com.coursejava.webservice.entities.Product;
import com.coursejava.webservice.repositories.CategoryRepository;
import com.coursejava.webservice.repositories.ProductRepository;
import com.coursejava.webservice.services.exceptions.DatabaseException;
import com.coursejava.webservice.services.exceptions.ResourceNotFoundException;
import com.coursejava.webservice.services.exceptions.ResourceNotFoundProductCategoryException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        try {
            Optional<Product> obj = productRepository.findById(id);
            return obj.orElseThrow(() -> new ResourceNotFoundException(id));
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Product insert(Product obj) {
        return productRepository.save(obj);
    }

    @Transactional
    public void addCategory(Long producId, Long categoryId) {
        try {
            Product product = productRepository.getReferenceById(producId);
            Category category = categoryRepository.getReferenceById(categoryId);

            product.getCategories().add(category);
        }
        catch(EntityNotFoundException e) {
            throw new ResourceNotFoundProductCategoryException(producId, categoryId);
        }
    }

    public void delete(Long id) {
        try {
            findById(id);
            productRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Long Id, Product obj) {
        try {
            Product entity = productRepository.getReferenceById(Id);
            updateData(entity, obj);
            return productRepository.save(entity);
        }
        catch(EntityNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setImgUrl(obj.getImgUrl());
    }
}
