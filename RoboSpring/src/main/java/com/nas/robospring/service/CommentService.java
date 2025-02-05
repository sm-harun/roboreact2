package com.nas.robospring.service;

import com.nas.robospring.model.Comment;
import com.nas.robospring.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Mono<Comment> createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Flux<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Mono<Void> deleteComment(Long commentId) {
        return commentRepository.deleteById(commentId);
    }
}
