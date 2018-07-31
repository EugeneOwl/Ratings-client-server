package com.example.server.transformer;

import com.example.server.dto.Dto;
import com.example.server.model.BaseEntity;

public interface Transformer<E extends BaseEntity, D extends Dto> {
    D transform(E entity);
    E transform(D entity);
}
