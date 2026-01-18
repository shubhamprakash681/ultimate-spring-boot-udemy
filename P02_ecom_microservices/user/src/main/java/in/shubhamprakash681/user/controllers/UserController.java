package in.shubhamprakash681.user.controllers;

import in.shubhamprakash681.user.dto.UserRequest;
import in.shubhamprakash681.user.dto.UserResponse;
import in.shubhamprakash681.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // for injecting final fields via constructor
@RequestMapping("/api/users") // optional: to set a base path for all endpoints in this controller
public class UserController {
    private final UserService userService;

    @GetMapping()
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
