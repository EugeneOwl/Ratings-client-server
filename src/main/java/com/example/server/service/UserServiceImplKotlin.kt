package com.example.server.service

import com.example.server.service.functions.getLongFromString;
import com.example.server.dto.UserDto
import com.example.server.dto.UserUpdateDtoKotlin
import com.example.server.model.User
import com.example.server.repository.UserRepository
import com.example.server.consts.MobileNumberConsts.Companion.NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN
import com.example.server.transformer.UserTransformer
import com.example.server.transformer.UserUpdateTransformerKotlin
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserServiceImplKotlin : UserServiceKotlin {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userTransformer: UserTransformer

    @Autowired
    private lateinit var userUpdateTransformerKotlin: UserUpdateTransformerKotlin

    override fun getUserById(id: Long): UserDto {
        val user: User = userRepository.getOne(id)
        log.info("User was taken by id: $user")

        return userTransformer.transform(user)
    }

    override fun updateUser(userUpdateDtoKotlin: UserUpdateDtoKotlin) {
        val user: User = userRepository.getOne(userUpdateDtoKotlin.id)
        user.update(userUpdateTransformerKotlin.transform(userUpdateDtoKotlin))

        userRepository.save(user)
        log.info("User was updated: $user")
    }

    override fun getAllUsers(): List<UserDto> =
            userRepository.findAll(Sort.by("id"))
                    .onEach { log.info("User was taken: $it") }
                    .map(userTransformer::transform)


    override fun cleanUpMobileNumber(mobileNumber: String): String =
            mobileNumber.replace(NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN, "")


    override fun getPageOfUsers(pageable: Pageable, filterPattern: String): Page<UserDto> =
            userRepository.findByIdOrUsername(
                    getLongFromString(filterPattern),
                    filterPattern.toLowerCase(),
                    pageable
            ).map(userTransformer::transform)
}