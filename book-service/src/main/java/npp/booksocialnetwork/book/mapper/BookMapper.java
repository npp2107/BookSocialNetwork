package npp.booksocialnetwork.book.mapper;

import npp.booksocialnetwork.book.dto.request.BookRequest;
import npp.booksocialnetwork.book.dto.response.BookResponse;
import npp.booksocialnetwork.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toBookResponse(Book book);
    @Mapping(source = "proposalStatus", target = "proposalStatus", ignore = true)
    Book toBook(BookRequest book);
}
