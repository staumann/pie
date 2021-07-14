package com.home.tool.web.api

import com.home.tool.model.Response
import com.home.tool.model.User
import com.home.tool.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("/user")
class UserRestController(private val userService: UserService) {

    @GetMapping("/get")
    fun getUser(@RequestParam("id") id: String): ResponseEntity<User> {
        userService.getById(id)?.apply {
            return ResponseEntity.ok().body(this)
        }
        return ResponseEntity.notFound().build()

    }

    @GetMapping("/store")
    fun storeUser(
        @RequestParam("id") id: String,
        @RequestParam("firstName") firstName: String,
        @RequestParam("lastName") lastName: String
    ): Response {
        val entity = userService.storeUser(User(id, firstName, lastName))
        return Response(true, "Store User ${entity.firstName} ${entity.lastName}")
    }

    @PostMapping("/store")
    fun storeUser(@RequestBody user: User): Response {
        val entity = userService.storeUser(user)
        return Response(true, "Store User ${entity.firstName} ${entity.lastName}")
    }

    @DeleteMapping("/delete")
    fun deleteUser(@RequestParam("id") id: String): Response {
        userService.deleteUser(id)
        return Response(true, "User with id $id was deleted.")
    }

    @GetMapping("/all")
    fun allUsers(): Iterable<User> {
        return userService.getAll()
    }
}
