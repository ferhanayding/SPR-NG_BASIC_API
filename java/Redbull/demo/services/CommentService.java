package Redbull.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Redbull.demo.entity.Comment;
import Redbull.demo.entity.Post;
import Redbull.demo.entity.User;
import Redbull.demo.repos.CommentRepository;
import Redbull.demo.requests.CommentCreateRequest;
import Redbull.demo.requests.CommentUpdateRequest;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	

	public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
		super();
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
		if(userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		}else if(userId.isPresent()) {
			return commentRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			return commentRepository.findByPostId(postId.get());
		}else {
			return commentRepository.findAll();
		}
		
		
	}

	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);

	}

	public Comment createOneComment(CommentCreateRequest newComment) {
		User user = userService.getOneUserById(newComment.getUserId());
		Post post = postService.getOnePostId(newComment.getPostId());
		if(user != null && post != null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(newComment.getId());
			commentToSave.setPost(post);
			commentToSave.setUser(user);
			commentToSave.setText(newComment.getText());
			commentRepository.save(commentToSave);
		}
		return null;
	}

	
	public Comment updateOnecommentId(Long commentId, CommentUpdateRequest updateComment) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment toUpdate = comment.get();
			toUpdate.setText(updateComment.getText());
			return commentRepository.save(toUpdate);
		}
		return null;
	}

	public void deleteOneComment(Long commentId) {
		
		 commentRepository.deleteById(commentId);
		
	}
}
