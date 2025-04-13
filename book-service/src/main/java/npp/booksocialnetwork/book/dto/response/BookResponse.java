package npp.booksocialnetwork.book.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse{
    private String id;

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

    private Double averageRating;
    private Integer totalRatings;
    private Integer favoriteCount;
    private Integer viewCount;
    private Boolean trending;

    private String createdByUserId;

    private Integer proposalStatus;
    private List<String> relatedBookIds;

    private Instant createdAt;
    private Instant updatedAt;
}
