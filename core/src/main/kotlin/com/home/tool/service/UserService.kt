package com.home.tool.service

import com.home.tool.repository.UserRepository
import com.home.tool.model.User
import org.springframework.stereotype.Service

interface UserService {
    fun getAll(): Iterable<User>

    fun getById(id: String): User?

    fun storeUser(user: User): User

    fun deleteUser(id: String)
}

@Service
class UserServiceImpl(private val userRep: UserRepository): UserService {

    override fun getAll(): Iterable<User> = userRep.findAll()

    override fun getById(id: String): User? = userRep.findById(id).orElse(null)

    override fun storeUser(user: User): User {
        user.id = if (user.id == "") getIdForString(user.firstName) else user.id
        return userRep.save(user)
    }

    override fun deleteUser(id: String) = userRep.deleteById(id)
}
