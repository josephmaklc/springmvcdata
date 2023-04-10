package com.optimal.solutions.springsample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimal.solutions.springsample.entities.Comment;
import com.optimal.solutions.springsample.entities.Post;
import com.optimal.solutions.springsample.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteCommentById(Long id) {
    	commentRepository.deleteById(id);
    }
    
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment Not Found!"));
    }
}
