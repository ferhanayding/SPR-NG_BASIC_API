package Redbull.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Redbull.demo.entity.Like;
import Redbull.demo.entity.Post;
import Redbull.demo.entity.User;
import Redbull.demo.repos.LikeRepository;

import Redbull.demo.requests.LikeCreateRequest;

@Service
public class LikeService {

		private LikeRepository likeRepository;
		private UserService userService;
		private PostService postService;
		
		public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
			super();
			this.likeRepository = likeRepository;
			this.userService = userService;
			this.postService = postService;
		}

		
		
		public List<Like> getAllLikesWithParam(Optional<Long> postId, Optional<Long> userId) {
			if(postId.isPresent() && userId.isPresent()) {
				return likeRepository.findByPostIdAndUserId(postId.get(),userId.get());
			}else if(postId.isPresent()) {
				return likeRepository.findByPostId(postId.get());
			}else if(userId.isPresent()) {
				return likeRepository.findByUserId(userId.get());
			}else {
				return likeRepository.findAll();
			}
		}

		public Like createOneLike(LikeCreateRequest newRequest) {
		User user = userService.getOneUserById(newRequest.getUserId());
		Post post = postService.getOnePostId(newRequest.getPostId());
		if(user != null && post != null) {
			Like likeToSave = new Like();
			likeToSave.setId(newRequest.getId());
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			return likeRepository.save(likeToSave);
		}
		
       return null;
		}

		public Like getOneLikeById(Long likeId) {

			return likeRepository.findById(likeId).orElse(null);
			
		}

		public void deleteOnebyLikeId(Long likeId) {
			likeRepository.deleteById(likeId);
		}

		
		
		
}
