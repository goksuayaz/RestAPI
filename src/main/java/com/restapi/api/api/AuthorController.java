package com.restapi.api.api;


import com.restapi.api.business.abs.IAuthorService;
import com.restapi.api.core.config.modelMapper.IModelMapperService;
import com.restapi.api.core.result.Result;
import com.restapi.api.core.result.ResultData;
import com.restapi.api.core.utilies.ResultHelper;
import com.restapi.api.dto.request.author.AuthorSaveRequest;
import com.restapi.api.dto.request.author.AuthorUpdateRequest;
import com.restapi.api.dto.response.author.AuthorResponse;
import com.restapi.api.dto.response.publisher.CursorResponse;
import com.restapi.api.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {

        Author saveAuthor =this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAuthor, AuthorResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") Long id) {
        Author author = this.authorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(author, AuthorResponse.class));
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize) {

        Page<Author> authorPage = this.authorService.cursor(page,pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage
                .map(author -> this.modelMapper.forResponse().map(author, AuthorResponse.class));

        return ResultHelper.cursor(authorResponsePage);
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author updateAuthor =this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAuthor, AuthorResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.authorService.delete(id);
        return ResultHelper.ok();
    }


}



