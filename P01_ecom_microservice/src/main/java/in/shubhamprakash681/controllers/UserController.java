package in.shubhamprakash681.controllers;

import java.util.List;

import in.shubhamprakash681.dto.UserRequest;
import in.shubhamprakash681.dto.UserResponse;
import org.springframework.web.bind.annotation.RestController;

import in.shubhamprakash681.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor // for injecting final fields via constructor
@RequestMapping("/api/users") // optional: to set a base path for all endpoints in this controller
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    // or
    // @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable long id) {
        UserResponse user = userService.getUserDetails(id);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/")
    public ResponseEntity<List<UserResponse>> createUser(@RequestBody UserRequest userReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable long id, @RequestBody UserRequest userReq) {
        UserResponse updatedUSer = userService.updateUser(id, userReq);

        if (updatedUSer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUSer);
    }
}
