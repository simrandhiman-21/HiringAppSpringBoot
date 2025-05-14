package com.learning.HiringApp.authMapper;

import com.learning.HiringApp.authDto.RegisterRequest;
import com.learning.HiringApp.authEntity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationName = "AuthUserMapperImpl", uses = UserMapperHelper.class)
public interface UserMapper {
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    User toUser(RegisterRequest registerRequest);
}