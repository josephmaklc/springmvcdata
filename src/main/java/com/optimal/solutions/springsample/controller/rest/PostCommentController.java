package com.optimal.solutions.springsample.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optimal.solutions.springsample.entities.Comment;
import com.optimal.solutions.springsample.entities.Post;
import com.optimal.solutions.springsample.services.CommentService;
import com.optimal.solutions.springsample.services.EmployeeService;
import com.optimal.solutions.springsample.services.PostService;

@RestController
@RequestMapping("/api/discussion")
public class PostCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private PostService postService;


    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @GetMapping(path = "/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) { 
    	System.out.println("trying to delete post: "+id);
    	Post p = postService.getPostById(id);
		if (p!=null) {
			postService.deletePostById(id);
			return ResponseEntity.ok("Post Deleted");
		}
		else {
			return ResponseEntity.notFound().build();
		}
    }

    @GetMapping(path = "/comments")
    public ResponseEntity<List<Comment>> getComments() {
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @PostMapping(path = "/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteComment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) { 
    	System.out.println("trying to delete comment: "+id);
    	Comment c = commentService.getCommentById(id);
		if (c!=null) {
			commentService.deleteCommentById(id);
			return ResponseEntity.ok("Comment Deleted");
		}
		else {
			return ResponseEntity.notFound().build();
		}
    }

    @GetMapping(path = "/testpost")
    public ResponseEntity<Post> createPost2(@RequestParam String title,@RequestParam String description, @RequestParam long employeeID) {
    	System.out.println("create post");
    	System.out.println("title:"+title);
    	System.out.println("desc:"+description);
    	System.out.println("employeeID:"+employeeID);
    	Post p = new Post();
    	p.setTitle(title);
    	p.setDescription(description);
    	p.setEmployee(employeeService.getEmployeeById(employeeID));
        return new ResponseEntity<>(postService.createPost(p), HttpStatus.CREATED);
    }

    @GetMapping(path = "/testcomment")
    public ResponseEntity<Comment> createComment(@RequestParam String comment,@RequestParam long employeeID,@RequestParam long postID) {
    	System.out.println("create comment");
    	System.out.println("comment:"+comment);
    	System.out.println("employeeID:"+employeeID);
    	System.out.println("postID:"+postID);
    	Comment c = new Comment();
    	c.setContent(comment);
    	c.setEmployeeId(employeeID);
    	c.setPostId(postID);
        return new ResponseEntity<>(commentService.createComment(c), HttpStatus.CREATED);
    }



}
