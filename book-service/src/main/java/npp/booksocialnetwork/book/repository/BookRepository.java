package npp.booksocialnetwork.book.repository;

import npp.booksocialnetwork.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    Page<Book> findAllByProposalStatus(Integer proposalStatus, Pageable pageable);
}