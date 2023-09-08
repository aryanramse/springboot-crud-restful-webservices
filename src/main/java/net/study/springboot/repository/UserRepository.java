package net.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.study.springboot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
