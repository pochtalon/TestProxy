package com.example.testforproxy.repository

import com.example.testforproxy.model.Post
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository extends MongoRepository<Post, String> {

}