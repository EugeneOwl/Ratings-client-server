package com.example.server.transformer

import com.example.server.dto.Dto
import com.example.server.model.BaseEntity

interface UpdateTransformerKotlin<E : BaseEntity, D : Dto> {
    fun transform(dtoKotlin: D): E
}