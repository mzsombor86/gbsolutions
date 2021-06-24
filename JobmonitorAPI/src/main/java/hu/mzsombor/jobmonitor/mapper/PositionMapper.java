package hu.mzsombor.jobmonitor.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.mzsombor.jobmonitor.dto.PositionPushDto;
import hu.mzsombor.jobmonitor.dto.PositionResultDto;
import hu.mzsombor.jobmonitor.model.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper {

	Position positionPushDtoToPosition(PositionPushDto dto);
	
	PositionResultDto positionToPositionResultDto(Position position);
	
	List<PositionResultDto> positionsToPositionResultDtos(List<Position> positions);

}
