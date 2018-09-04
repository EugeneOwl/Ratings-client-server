package com.example.server.consts

class MobileNumberConsts {
    companion object {
        const val MOBILE_NUMBER_PATTERN: String = "^((375)([0-9]{9}))$"
        const val NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN: String = "[^0-9]"
    }
}