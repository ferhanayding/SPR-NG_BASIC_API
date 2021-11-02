package Redbull.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Redbull.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

}
