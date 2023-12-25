package com.example.testforproxy.repository

import com.example.testforproxy.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email)
}