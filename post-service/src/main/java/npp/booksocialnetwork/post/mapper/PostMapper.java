package npp.booksocialnetwork.post.mapper;

import npp.booksocialnetwork.post.dto.response.PostResponse;
import npp.booksocialnetwork.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}