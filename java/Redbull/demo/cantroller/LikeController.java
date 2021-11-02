package Redbull.demo.cantroller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Redbull.demo.entity.Like;
import Redbull.demo.requests.LikeCreateRequest;
import Redbull.demo.services.LikeService;
@RestController
@RequestMapping("/likes")
public class LikeController {

	
		private LikeService likeService;

		public LikeController(LikeService likeService) {
			super();
			this.likeService = likeService;
		}
		
		@GetMapping
		public List<Like> getAllLikes(@RequestParam Optional<Long> postId , @RequestParam Optional<Long> userId ){
			return likeService.getAllLikesWithParam(postId,userId);
		}
		@PostMapping
		public Like createOneLike(@RequestBody LikeCreateRequest newRequest) {
			return likeService.createOneLike(newRequest);
		}
		@GetMapping("/{likeId}")
		public Like getOneLike(@PathVariable Long likeId) {
			return likeService.getOneLikeById(likeId);
		}
		
		@DeleteMapping("/{likeId}")
		public void deleteOneLike(@PathVariable Long likeId) {
			likeService.deleteOnebyLikeId(likeId);
		}
}
