package com.restapi.api.api;

import com.restapi.api.business.abs.IBookBorrowingService;
import com.restapi.api.business.abs.IBookService;
import com.restapi.api.core.config.modelMapper.IModelMapperService;
import com.restapi.api.core.result.Result;
import com.restapi.api.core.result.ResultData;
import com.restapi.api.core.utilies.ResultHelper;
import com.restapi.api.dto.request.bookBorrowing.BookBorrowingSaveRequest;
import com.restapi.api.dto.request.bookBorrowing.BookBorrowingUpdateRequest;
import com.restapi.api.dto.response.bookBorrowing.BookBorrowingResponse;
import com.restapi.api.dto.response.publisher.CursorResponse;
import com.restapi.api.entities.Book;
import com.restapi.api.entities.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book-borrowings")
public class BookBorrowingController {


    private final IBookBorrowingService bookBorrowingService;
    private final IModelMapperService modelMapper;
    private final IBookService bookService;


    public BookBorrowingController(IBookBorrowingService bookBorrowingService, IModelMapperService modelMapper, IBookService bookService) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {

        BookBorrowing saveBookBorrowing =this.modelMapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);

        Book book = this.bookService.getById(bookBorrowingSaveRequest.getBookId());
        saveBookBorrowing.setBook(book);

        this.bookBorrowingService.save(saveBookBorrowing);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBookBorrowing, BookBorrowingResponse.class));
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        BookBorrowing updateBookBorrowing =this.modelMapper.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);
        this.bookBorrowingService.update(updateBookBorrowing);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateBookBorrowing, BookBorrowingResponse.class));
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.bookBorrowingService.delete(id);
        return ResultHelper.ok();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") Long id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class));
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize) {

        Page<BookBorrowing> bookBorrowingPage = this.bookBorrowingService.cursor(page,pageSize);
        Page<BookBorrowingResponse> bookBorrowingResponsePage = bookBorrowingPage
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class));

        return ResultHelper.cursor(bookBorrowingResponsePage);
    }


}