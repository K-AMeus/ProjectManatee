package ee.cyber.manatee.mapper;

import ee.cyber.manatee.dto.InterviewDto;
import ee.cyber.manatee.model.Interview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Maps between Interview entity and InterviewDto data transfer object.
 */
@Mapper(componentModel = "spring")
public interface InterviewMapper {
    InterviewMapper INSTANCE = Mappers.getMapper(InterviewMapper.class);

    @Mapping(source = "applicationId", target = "application.id")
    Interview toEntity(InterviewDto interviewDto);

    @Mapping(source = "application.id", target = "applicationId")
    InterviewDto toDto(Interview interview);
}
