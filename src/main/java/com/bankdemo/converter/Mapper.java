package com.bankdemo.converter;

public interface Mapper <E , D> {

    D fromEntityToDto (E entity);

    E fromDtoToEntity (D dto);
}
