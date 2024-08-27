package pet.soilplotservice.service.mapper;

import org.mapstruct.Mapper;
import pet.soilplotservice.config.MapperConfig;
import pet.soilplotservice.dto.LandPlotRequestDto;
import pet.soilplotservice.dto.LandPlotResponseDto;
import pet.soilplotservice.model.LandPlot;

@Mapper(config = MapperConfig.class)
public interface LandPlotMapper {
    LandPlotResponseDto toDto(LandPlot landPlot);

    LandPlot toEntity(LandPlotRequestDto landPlotRequestDto);
}
