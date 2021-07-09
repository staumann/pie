package com.home.tool.service

import com.home.tool.repository.UserRepository
import com.home.tool.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRep: UserRepository) {

    fun getAll(): Iterable<User> = userRep.findAll()

    fun getById(id: String): User? = userRep.findById(id).orElse(null)

    fun storeUser(user: User): User {
        user.id = if (user.id == "") getIdForString(user.firstName) else user.id
        return userRep.save(user)
    }

    fun deleteUser(id: String) = userRep.deleteById(id)
}