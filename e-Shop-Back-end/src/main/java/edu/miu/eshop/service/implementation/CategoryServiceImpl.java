package edu.miu.eshop.service.implementation;

import edu.miu.eshop.model.Category;
import edu.miu.eshop.repository.CategoryRepository;
import edu.miu.eshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllMain() {
        return categoryRepository.findAll()
                .stream()
                .filter(category -> category.getParent() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getSubById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent())
            return category.get().getSubCategories();
        return new ArrayList<>();
    }
}
