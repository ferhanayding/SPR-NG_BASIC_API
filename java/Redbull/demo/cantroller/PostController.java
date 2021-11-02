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

import Redbull.demo.entity.Post;
import Redbull.demo.requests.PostCreateRequest;
import Redbull.demo.requests.PostUpdateRequest;
import Redbull.demo.responses.PostResponse;
import Redbull.demo.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

	
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	
	
	@GetMapping
	public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
		return postService.getAllPosts(userId);
	}
	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable Long postId) {
		return postService.getOnePostId(postId);
	}
	@PostMapping
	public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
		return postService.createOnePost(newPostRequest);
	}
	@PutMapping("/{postId}")
	public Post updateOnePost(@PathVariable Long postId,@RequestBody PostUpdateRequest updatePost) {
		return postService.updateOnePostById(postId,updatePost);
	}
	@DeleteMapping("/{postId}")
	public void deleteOnePost(@PathVariable Long postId) {
		postService.deleteOnePostById(postId);
	}
	
	
	
	
}
