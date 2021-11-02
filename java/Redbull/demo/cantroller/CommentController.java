package Redbull.demo.cantroller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Redbull.demo.entity.Comment;
import Redbull.demo.requests.CommentCreateRequest;
import Redbull.demo.requests.CommentUpdateRequest;
import Redbull.demo.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@GetMapping
	public List<Comment> getAllComments(@RequestParam Optional<Long> userId, 
			@RequestParam Optional<Long> postId){
		return commentService.getAllCommentsWithParam(userId, postId);
	}
	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneCommentById(commentId);
	}
	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest newComment) {
		return commentService.createOneComment(newComment);
	}
	@PutMapping("/{commentId}")
	public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateComment) {
		return commentService.updateOnecommentId(commentId,updateComment);
	}
	@DeleteMapping("/{commentId}")
	public void deleteOneComment (@PathVariable Long commentId) {
		 commentService.deleteOneComment(commentId);
	}
	
} 
