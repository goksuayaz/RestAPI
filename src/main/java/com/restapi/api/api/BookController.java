package com.restapi.api.api;

import com.restapi.api.business.abs.*;
import com.restapi.api.core.result.ResultData;
import com.restapi.api.core.utilies.ResultHelper;
import com.restapi.api.dto.request.book.BookSaveRequest;
import com.restapi.api.dto.request.book.BookUpdateRequest;
import com.restapi.api.dto.response.author.AuthorResponse;
import com.restapi.api.dto.response.book.BookResponse;
import com.restapi.api.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
    public class BookController {

        private final IBookService bookService;
        private final aaaa modelMapper;
        private final IAuthorService authorService;
        private final IPublisherService publisherService;
        private final IBookBorrowingService bookBorrowingService;
        private final ICategoryService categoryService;


        public BookController(
                IBookService bookService,
                aaaa modelMapper,
                IAuthorService authorService,
                IPublisherService publisherService,
                IBookBorrowingService bookBorrowingService, ICategoryService categoryService) {
            this.bookService = bookService;
            this.modelMapper = modelMapper;
            this.authorService = authorService;
            this.publisherService = publisherService;
            this.bookBorrowingService = bookBorrowingService;
            this.categoryService = categoryService;
        }

        @PostMapping()
        @ResponseStatus(HttpStatus.CREATED)
        public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
            Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

            Author author = this.authorService.get(bookSaveRequest.getAuthorId());
            saveBook.setAuthor(author);

            Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
            saveBook.setPublisher(publisher);

            this.bookService.save(saveBook);
            return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));
        }

        @GetMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResultData<BookResponse> get(@PathVariable("id") Long id) {
            Book book = this.bookService.get(id);
            return ResultHelper.success(this.modelMapper.forResponse().map(book, BookResponse.class));
        }

        @GetMapping("/{id}/author")
        @ResponseStatus(HttpStatus.OK)
        public ResultData<AuthorResponse> getAuthor(@PathVariable("id") Long id) {
            Book book = this.bookService.get(id);
            return ResultHelper.success(this.modelMapper.forResponse().map(book.getAuthor(), AuthorResponse.class));
        }


        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Result delete(@PathVariable("id") Long id) {
            this.bookService.delete(id);
            return ResultHelper.ok();
        }


        @GetMapping()
        @ResponseStatus(HttpStatus.OK)
        public ResultData<CursorResponse<BookResponse>> cursor(
                @RequestParam(name = "page",required = false,defaultValue = "0") int page,
                @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize) {

            Page<Book> bookPage = this.bookService.cursor(page,pageSize);
            Page<BookResponse> bookResponsePage = bookPage
                    .map(book -> this.modelMapper.forResponse().map(book, BookResponse.class));

            return ResultHelper.cursor(bookResponsePage);
        }

        @PutMapping()
        @ResponseStatus(HttpStatus.OK)
        public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
            Book updateBook =this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
            this.bookService.update(updateBook);
            return ResultHelper.success(this.modelMapper.forResponse().map(updateBook, BookResponse.class));
        }

    }


