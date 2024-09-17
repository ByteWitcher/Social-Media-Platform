package org.sop.postservice.services.facade;

import org.sop.postservice.models.Post;

import java.util.List;

public interface PostService {
    List<Post> findByUserId(Long userId);

    List<Post> findUnviewedPostsByUserId(Long userId, int page, int size);

    void deleteById(Long id);

    void deleteByUserId(Long userId);

    Post save(Post post);

    Post update(Post post);
}
