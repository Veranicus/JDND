package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.LoginUserRequest;
import com.example.demo.security.JWTAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    private UserRepository userRepository;


    private CartRepository cartRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private JWTAuthenticationFilter jwtAuthenticationFilter;

    public UserController() {
    }

    @Autowired
    public UserController(UserRepository userRepository, CartRepository cartRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        log.info("Username set with {} ", createUserRequest.getUsername());
        Cart cart = new Cart();
        cartRepository.save(cart);
        user.setCart(cart);
        if (createUserRequest.getPassword().length() < 7 || !createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
            System.out.println("Wrong password...");
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        User user = new User();
        user.setUsername(loginUserRequest.getUsername());
        log.info("User {} logs in ", loginUserRequest.getUsername());
        if (loginUserRequest.getPassword().length() < 7) {
            System.out.println("Wrong password...");
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(bCryptPasswordEncoder.encode(loginUserRequest.getPassword()));
        if (userRepository.findByUsername(loginUserRequest.getUsername()) != null) {
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }

}
