package com.restapi.api.api;

import com.restapi.api.business.abs.ICategoryService;
import com.restapi.api.core.config.modelMapper.ModelMapperService;
import com.restapi.api.core.result.ResultData;
import com.restapi.api.core.utilies.ResultHelper;
import com.restapi.api.dto.request.category.CategorySaveRequest;
import com.restapi.api.dto.request.category.CategoryUpdateRequest;
import com.restapi.api.dto.response.category.CategoryResponse;
import com.restapi.api.dto.response.category.CategoryResultResponse;
import com.restapi.api.dto.response.publisher.CursorResponse;
import com.restapi.api.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    private final ModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, ModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {

        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class));
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest, Category.class);
        this.categoryService.update(updateCategory);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCategory, CategoryResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResultResponse delete(@PathVariable("id") Long id) {

        return categoryService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") Long id) {
        Category category = this.categoryService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(category, CategoryResponse.class));
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Category> categoryPage = this.categoryService.cursor(page, pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category, CategoryResponse.class));

        return ResultHelper.cursor(categoryResponsePage);
    }

}
