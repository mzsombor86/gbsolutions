package hu.mzsombor.jobmonitor.mapper;

import org.mapstruct.Mapper;

import hu.mzsombor.jobmonitor.dto.PositionPushDto;
import hu.mzsombor.jobmonitor.model.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper {

	Position positionPushDtoToPosition(PositionPushDto dto);

}
