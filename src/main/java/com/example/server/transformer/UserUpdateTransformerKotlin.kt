package com.example.server.transformer

import com.example.server.dto.UserUpdateDtoKotlin
import com.example.server.model.Role
import com.example.server.model.User
import com.example.server.service.RoleService
import com.example.server.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserUpdateTransformerKotlin : UpdateTransformerKotlin<User, UserUpdateDtoKotlin> {

    @Autowired
    private lateinit var roleService: RoleService

    @Autowired
    private lateinit var userService: UserService

    override fun transform(dtoKotlin: UserUpdateDtoKotlin): User {
        val user = User()
        user.roles = HashSet<Role>(roleService.getRoleListByIds(dtoKotlin.roleIds))
        user.mobileNumber = userService.cleanUpMobileNumber(dtoKotlin.mobileNumber)
        user.id = dtoKotlin.id

        return user
    }
}