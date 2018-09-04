package com.example.server.dto

import com.example.server.consts.MobileNumberConsts.Companion.MOBILE_NUMBER_PATTERN
import javax.validation.constraints.Pattern

data class UserUpdateDtoKotlin(val id: Long,
                               @field:Pattern(regexp = MOBILE_NUMBER_PATTERN,
                                       message = "Mobile number should match.")
                               val mobileNumber: String,
                               val roleIds: List<Long>) : Dto