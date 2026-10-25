package com.beautyshop.crud;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.beautyshop.crud.exception.ResourceNotFoundException;
import com.beautyshop.crud.model.Product;
import com.beautyshop.crud.repository.CategoryRepository;
import com.beautyshop.crud.repository.ProductRepository;
import com.beautyshop.crud.service.impl.ProductServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class ProductServiceImplTest {
    private final ProductRepository repo = mock(ProductRepository.class);
    private final CategoryRepository catRepo = mock(CategoryRepository.class);
    private final ProductServiceImpl service = new ProductServiceImpl(repo, catRepo);

    private Product product(String categoryId) {
        Product p = new Product();
        p.setName("Sérum"); p.setPrice(19.9); p.setStock(5); p.setCategoryId(categoryId);
        return p;
    }

    @Test void addSavesWhenCategoryExists() {
        when(catRepo.existsById("c1")).thenReturn(true);
        when(repo.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));
        assertEquals("Sérum", service.add(product("c1")).getName());
    }

    @Test void addFailsWhenCategoryMissing() {
        when(catRepo.existsById("zz")).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> service.add(product("zz")));
        verify(repo, never()).save(any());
    }

    @Test void modifyFailsWhenProductMissing() {
        when(repo.existsById("p9")).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> service.modify("p9", product("c1")));
    }

    @Test void deleteCallsRepository() {
        when(repo.existsById("p1")).thenReturn(true);
        service.delete("p1");
        verify(repo).deleteById("p1");
    }

    @Test void showByIdThrowsWhenMissing() {
        when(repo.findById("p4")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.showById("p4"));
    }
}
