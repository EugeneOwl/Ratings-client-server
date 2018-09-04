package com.example.server.service.functions

/**
 * Returns Long number from string if it contains or 0 if it doesn't.
 * e.g.
 *      "abc" returns 0
 *      "a911 bc" returns 911
 *      "a91 b 1c" returns 911
 *      " 911 " returns 911
 */
fun getLongFromString(str: String): Long =
        str.replace(Regex("[^\\d]"), "").toLongOrNull() ?: 0L