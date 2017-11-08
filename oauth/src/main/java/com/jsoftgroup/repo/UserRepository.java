package com.jsoftgroup.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsoftgroup.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
