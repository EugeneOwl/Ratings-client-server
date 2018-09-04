package com.example.server.controller

import com.example.server.dto.UserDto
import com.example.server.dto.UserUpdateDtoKotlin
import com.example.server.service.UserServiceKotlin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RequestMapping(value = ["server/users"], produces = [MediaType.APPLICATION_JSON_VALUE])
open class UserControllerKotlin {

    @Autowired
    private lateinit var userServiceKotlin: UserServiceKotlin

    @GetMapping
    open fun getAllUsers(): List<UserDto> = userServiceKotlin.getAllUsers()


    @GetMapping("{id}")
    open fun getUserById(@PathVariable("id") id: Long): UserDto = userServiceKotlin.getUserById(id)


    @GetMapping("/page")
    open fun getPageOfUsers(
            pageable: Pageable,
            @RequestParam(value = "filterPattern", defaultValue = "") filterPattern: String
    ): Page<UserDto> = userServiceKotlin.getPageOfUsers(pageable, filterPattern)


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    open fun updateUser(@Valid @RequestBody userUpdateDtoKotlin: UserUpdateDtoKotlin): UserUpdateDtoKotlin {
        userServiceKotlin.updateUser(userUpdateDtoKotlin)

        return userUpdateDtoKotlin
    }
}