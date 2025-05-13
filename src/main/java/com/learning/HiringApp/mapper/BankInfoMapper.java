package com.learning.HiringApp.mapper;


import com.learning.HiringApp.dtos.BankInfoDTO;
import com.learning.HiringApp.entity.BankInfo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BankInfoMapper {

    @Mapping(source = "candidateId", target = "candidate.id")
    BankInfo toEntity(BankInfoDTO dto);

    @Mapping(source = "candidate.id", target = "candidateId")
    BankInfoDTO toDTO(BankInfo entity);
}
