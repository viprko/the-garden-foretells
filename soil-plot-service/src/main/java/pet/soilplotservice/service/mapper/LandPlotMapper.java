package pet.soilplotservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet.soilplotservice.config.MapperConfig;
import pet.soilplotservice.dto.LandPlotRequestDto;
import pet.soilplotservice.dto.LandPlotResponseDto;
import pet.soilplotservice.model.LandPlot;

@Mapper(config = MapperConfig.class)
public interface LandPlotMapper {
    @Mapping(target = "userId", ignore = true)
    LandPlotResponseDto toDto(LandPlot landPlot);

    LandPlot toEntity(LandPlotRequestDto landPlotRequestDto);
}
