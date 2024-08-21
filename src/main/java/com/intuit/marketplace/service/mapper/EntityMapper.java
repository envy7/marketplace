package com.intuit.marketplace.service.mapper;

public interface EntityMapper<ReqDto, ResDto, E> {
    public E toEntity(ReqDto dto);
    public ResDto toDto(E entity);
}
