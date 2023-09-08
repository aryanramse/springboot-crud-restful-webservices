package net.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.study.springboot.entity.User;
import net.study.springboot.exception.ResourceNotFoundException;
import net.study.springboot.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	//get all users
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value="id") long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id not found: " + userId));
	}
	
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		User existingUser = userRepository.save(user);
		return existingUser;
	}
	
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id not found: " + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return userRepository.save(existingUser);
	}
	
	//delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id not found: " + userId));
		userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
}
