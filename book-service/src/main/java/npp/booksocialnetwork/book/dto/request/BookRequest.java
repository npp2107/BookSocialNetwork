package npp.booksocialnetwork.book.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {
    private String title;
    private String subtitle;
    private String author;
    private String publisher;
    private LocalDate publicationDate;
    private String description;

    private String contentPreview;
    private String externalLink;

    private List<String> genres;
    private List<String> tags;
    private String language;
    private String coverImageUrl;
    private String isbn;

    private Integer proposalStatus;
    private List<String> relatedBookIds;
}
