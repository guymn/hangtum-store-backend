package hangtum.store.item.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hangtum.store.item.model.Category;
import hangtum.store.item.service.CategotyControllerService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategotyControllerService categotyControllerService;

    @Autowired
    public CategoryController(CategotyControllerService categotyControllerService) {
        this.categotyControllerService = categotyControllerService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategorys() {
        List<Category> categories = categotyControllerService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id) {
        Optional<Category> optCategory = categotyControllerService.getCategoryById(id);
        if (!optCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optCategory);
    }

    @PostMapping
    public ResponseEntity<String> postCategory(@RequestBody Category category) {
        categotyControllerService.saveCategory(category);
        return ResponseEntity.ok("Category was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> optCategory = categotyControllerService.getCategoryById(id);
        if (!optCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        category.setId(id);
        categotyControllerService.saveCategory(category);
        return ResponseEntity.ok("Category was updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categotyControllerService.deleteCategory(id);
        return ResponseEntity.ok("Category was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCategory() {
        categotyControllerService.deleteAllCategory();
        return ResponseEntity.ok("Category was Deleted");
    }
}
