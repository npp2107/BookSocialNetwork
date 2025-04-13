package npp.booksocialnetwork.book.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Document(value = "book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @MongoId
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

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
