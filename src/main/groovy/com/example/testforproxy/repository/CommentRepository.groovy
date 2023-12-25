package com.example.testforproxy.repository

import com.example.testforproxy.model.Comment
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository extends MongoRepository<Comment, String> {

}