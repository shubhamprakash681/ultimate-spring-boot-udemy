package in.shubhamprakash681.services;

import java.util.List;
import java.util.Objects;

import in.shubhamprakash681.dto.AddressDto;
import in.shubhamprakash681.dto.UserRequest;
import in.shubhamprakash681.dto.UserResponse;
import in.shubhamprakash681.models.Address;
import org.springframework.stereotype.Service;

import in.shubhamprakash681.models.User;
import in.shubhamprakash681.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream().map(this::mapToUserResponse)
                .toList();
    }

    public UserResponse getUserDetails(long id) {
        return userRepository.findById(id).map(this::mapToUserResponse).orElse(null);
    }

    public List<UserResponse> addUser(UserRequest userReq) {
        userRepository.save(mapUserRequestToUser(userReq));

        return userRepository.findAll().stream().map(this::mapToUserResponse).toList();
    }

    public UserResponse updateUser(long id, UserRequest userReq) {
        User user = mapUserRequestToUser(userReq);
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());

            userRepository.save(existingUser);

            return userRepository.findById(id).map(this::mapToUserResponse).orElse(null);
        }).orElse(null);
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());

        if (user.getAddress() != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setZipcode(user.getAddress().getZipcode());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            userResponse.setAddress(addressDto);
        }

        return userResponse;
    }

    private User mapUserRequestToUser(UserRequest userReq) {
        User user = new User();

        if (userReq.getId() != null) user.setId(Long.valueOf(userReq.getId()));
        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setEmail(userReq.getEmail());
        user.setPhone(userReq.getPhone());
        user.setRole(userReq.getRole());

        if (userReq.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userReq.getAddress().getStreet());
            address.setCity(userReq.getAddress().getCity());
            address.setZipcode(userReq.getAddress().getZipcode());
            address.setState(userReq.getAddress().getState());
            address.setCountry(userReq.getAddress().getCountry());

            user.setAddress(address);
        }

        return user;
    }
}
