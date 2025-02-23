package modul2proiect.bookstore.controller;

import modul2proiect.bookstore.dto.UserDTO;
import modul2proiect.bookstore.entities.User;
import modul2proiect.bookstore.mapper.UserMapper;
import modul2proiect.bookstore.repository.UserRepository;
import modul2proiect.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        User userToCreate = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(userToCreate);
        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }
    @PutMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody UserDTO updatedUserDTO) {
        User userToUpdate = UserMapper.userDTO2User(updatedUserDTO);
        User updatedUser = userService.verify(userToUpdate.getEmail(), userToUpdate.getVerificationCode());
        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO updatedUserDTO) {
        User userToLogin = UserMapper.userDTO2User(updatedUserDTO);
        User loggedinUser = userService.login(userToLogin.getEmail(), userToLogin.getPassword());
        return ResponseEntity.ok(UserMapper.user2UserDTO(loggedinUser));

    }

}
