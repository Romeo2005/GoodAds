package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.user.User

interface UserRepository {
    suspend fun login(username: String, password: String): User
    suspend fun changePrices(postPrice: Int, storyPrice: Int)
    suspend fun myUser(): User
    suspend fun getUser(uid: String): User
    suspend fun getUsers(): List<User>
    suspend fun getToken(): String?
    suspend fun applyMyAd(userId: String, adId: String)
}