package Redbull.demo.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Redbull.demo.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	List<Like> findByPostIdAndUserId(Long postId, Long userId);

	List<Like> findByPostId(Long postId);

	List<Like> findByUserId(Long userId);

}
