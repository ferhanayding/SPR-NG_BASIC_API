package Redbull.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import Redbull.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
