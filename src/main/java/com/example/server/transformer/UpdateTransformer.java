package com.example.server.transformer;

import com.example.server.dto.Dto;
import com.example.server.model.BaseEntity;

public interface UpdateTransformer<E extends BaseEntity, D extends Dto> {
    E transform(D entity);
}
