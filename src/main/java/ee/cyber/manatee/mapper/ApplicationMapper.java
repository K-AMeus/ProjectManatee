package ee.cyber.manatee.mapper;


import java.util.List;

import org.mapstruct.Mapper;

import ee.cyber.manatee.dto.ApplicationDto;
import ee.cyber.manatee.model.Application;
import org.mapstruct.Mapping;


/**
 * Maps between Application entity and ApplicationDto data transfer object.
 * Uses InterviewMapper for nested mapping of interview details.
 */
@Mapper(componentModel = "spring", uses = InterviewMapper.class)
public interface ApplicationMapper {

    @Mapping(source = "interviews", target = "interviews")
    ApplicationDto entityToDto(Application entity);

    Application dtoToEntity(ApplicationDto dto);

    List<ApplicationDto> entitiesToDtoList(List<Application> entities);
}