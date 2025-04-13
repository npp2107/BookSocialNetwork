package npp.booksocialnetwork.book.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import npp.booksocialnetwork.book.dto.ApiResponse;
import npp.booksocialnetwork.book.dto.PageResponse;
import npp.booksocialnetwork.book.dto.request.BookRequest;
import npp.booksocialnetwork.book.dto.response.BookResponse;
import npp.booksocialnetwork.book.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Book Controller", description = "Controller for Book APIs")
public class BookController {
    BookService bookService;

    @Operation(summary = "Create Book", description = "API for Create Book")
    @PostMapping
    ApiResponse<BookResponse> create (@RequestBody BookRequest bookRequest){
        return ApiResponse.<BookResponse>builder()
                .result(bookService.createBook(bookRequest))
                .build();
    }

    @Operation(summary = "Get All Book", description = "API for Get All Book")
    @GetMapping
    ApiResponse<PageResponse<BookResponse>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
            ){
        return ApiResponse.<PageResponse<BookResponse>>builder()
                .result(bookService.getAll(page, size))
                .build();
    }
}
