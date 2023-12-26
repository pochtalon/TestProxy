package com.example.testforproxy.service.impl

import com.example.testforproxy.dto.post.PostDto
import com.example.testforproxy.dto.user.UserInfoDto
import com.example.testforproxy.dto.user.UserUpdateRequestDto
import com.example.testforproxy.dto.user.auth.UserRegistrationRequestDto
import com.example.testforproxy.dto.user.auth.UserResponseDto
import com.example.testforproxy.exception.EntityNotFoundException
import com.example.testforproxy.exception.RegistrationException
import com.example.testforproxy.mapper.PostMapper
import com.example.testforproxy.mapper.UserMapper
import com.example.testforproxy.model.User
import com.example.testforproxy.repository.UserRepository
import com.example.testforproxy.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService {
    @Autowired final UserRepository userRepository
    @Autowired final PasswordEncoder passwordEncoder
    @Autowired final UserMapper userMapper
    @Autowired final PostMapper postMapper

    @Override
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException {
        if (userRepository.findByEmail(request.email) != null ||
                !(request.password == request.passwordRepeat)) {
            throw new RegistrationException("Unable to complete registration")
        }
        User user = userMapper.toModel(request)
        user.password = passwordEncoder.encode(request.password)
        user.friends = Collections.emptySet()
        user.posts = Collections.emptyList()
        user.roles = Set.of(User.RoleName.ROLE_USER)
        User savedUser = userRepository.save(user)
        userMapper.toDto(savedUser)
    }

    @Override
    UserInfoDto getUserInfo(User user) {
        userMapper.toInfoDto(user)
    }

    @Override
    UserInfoDto updateUserInfo(User user, UserUpdateRequestDto infoRequest) {
        userUpdate(user, infoRequest)
        userMapper.toInfoDto(userRepository.save(user))
    }

    @Override
    void connect(User user, String friendId) {
        def friend = userRepository.findById(friendId).orElseThrow(() ->
            new EntityNotFoundException("User with id " + friendId + " was not found"))
        user.friends.add(friend)
        userRepository.save(user)
    }

    @Override
    void disconnect(User user, String friendId) {
        def friend = userRepository.findById(friendId).orElseThrow(() ->
                new EntityNotFoundException("User with id " + friendId + " was not found"))
        user.friends.removeElement(friend)
        userRepository.save(user)
    }

    @Override
    void deleteUser(User user) {
        userRepository.delete(user)
    }

    @Override
    List<PostDto> getFeed(User user, Pageable pageable) {
        def friendIds = user.friends.stream()
                .map { it.id }
                .collect()

        def friends = userRepository.findAllById { friendIds }
        friends.stream()
        .flatMap {it.posts::stream()}
        .sorted((p1, p2) -> p2.time <=> p1.time)
        .skip(pageable.pageNumber * pageable.pageSize)
        .limit(pageable.pageSize)
        .map {postMapper::toDto}
        .collect()
    }

    private void userUpdate(User user, UserUpdateRequestDto infoRequest) {
        if (infoRequest.password != null
                && infoRequest.passwordRepeat != null
                && infoRequest.password == infoRequest.passwordRepeat) {
            user.password = passwordEncoder.encode(infoRequest.password)
        }
        if (infoRequest.email != null) {
            user.email = infoRequest.email
        }
        if (infoRequest.firstName != null) {
            user.firstName = infoRequest.firstName
        }
        if (infoRequest.lastName != null) {
            user.lastName = infoRequest.lastName
        }
    }
}
