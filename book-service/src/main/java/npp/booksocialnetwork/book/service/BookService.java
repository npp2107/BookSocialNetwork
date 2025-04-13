package npp.booksocialnetwork.book.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import npp.booksocialnetwork.book.constant.PredefinedRole;
import npp.booksocialnetwork.book.dto.PageResponse;
import npp.booksocialnetwork.book.dto.enums.ProposalStatusCode;
import npp.booksocialnetwork.book.dto.request.BookRequest;
import npp.booksocialnetwork.book.dto.response.BookResponse;
import npp.booksocialnetwork.book.mapper.BookMapper;
import npp.booksocialnetwork.book.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;

    @Transactional
    public BookResponse createBook (BookRequest bookRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var isAdminRole = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().contains(PredefinedRole.ADMIN_ROLE));

        var book = bookMapper.toBook(bookRequest);
        book.setCreatedByUserId(authentication.getName());

        if (isAdminRole)
            book.setProposalStatus(ProposalStatusCode.APPROVED.getValue());
        else
            book.setProposalStatus(ProposalStatusCode.PENDING.getValue());
        book = bookRepository.save(book);

        return bookMapper.toBookResponse(book);
    }

    public PageResponse<BookResponse> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var isAdminRole = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().contains(PredefinedRole.ADMIN_ROLE));
        var pageData = isAdminRole ?
                bookRepository.findAll(pageable) :
                bookRepository.findAllByProposalStatus(ProposalStatusCode.APPROVED.getValue(), pageable);
        return PageResponse.<BookResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().map(bookMapper::toBookResponse).toList())
                .build();
    }
}
