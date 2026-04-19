    package ch.wiss.wiss_quiz.controller;



    import ch.wiss.wiss_quiz.dto.CategoryDTO;
    import org.springframework.web.bind.annotation.*;

    import ch.wiss.wiss_quiz.model.Category;
    import ch.wiss.wiss_quiz.model.CategoryRepository;

    @RestController
    @RequestMapping(path = "/category")
    public class CategoryController {


        /**
         * Dependency Injetion auf Repository
         */
        private CategoryRepository categoryRepository;


        public CategoryController(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        @CrossOrigin(origins = "http://localhost:3000")
        @GetMapping(path = "")
        public Iterable<Category> getAllCategories() {
            return categoryRepository.findAll();
        }



        @PostMapping(path="/addcategory")
        public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
            // 1. DTO -> Entity umwandeln
            Category category = new Category();
            category.setName(categoryDTO.getName());

            // 2. Speichern
            Category savedCategory = categoryRepository.save(category);

            // 3. Entity -> DTO zurückgeben
            return new CategoryDTO(
                    savedCategory.getId(),
                    savedCategory.getName()
            );
        }







    }
