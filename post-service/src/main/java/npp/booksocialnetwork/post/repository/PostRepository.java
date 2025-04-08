package npp.booksocialnetwork.post.repository;

import npp.booksocialnetwork.post.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}