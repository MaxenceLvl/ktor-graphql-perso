package com.example.repository

import com.example.models.Review
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import org.litote.kmongo.*

class ReviewRepository(client: MongoClient) : IRepository<Review> {

    override lateinit var col: MongoCollection<Review>

    init {
        val database = client.getDatabase("myFirstDatabase")
        col = database.getCollection<Review>("Review")
    }

    fun getReviewsByDessertId(dessertId: String): List<Review> {
        return try {
            val res = col.find(Review::dessertId eq dessertId) ?: throw Exception("No revie< with dessert ID exists")
            res.asIterable().map { it }
        } catch (t: Throwable) {
            throw Exception("Cannot find reviews")
        }
    }

}