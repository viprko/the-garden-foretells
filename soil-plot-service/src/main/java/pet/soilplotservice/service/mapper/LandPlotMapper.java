package pet.soilplotservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pet.soilplotservice.config.MapperConfig;
import pet.soilplotservice.dto.LandPlotRequestDto;
import pet.soilplotservice.dto.LandPlotResponseDto;
import pet.soilplotservice.model.LandPlot;

@Mapper(config = MapperConfig.class)
public interface LandPlotMapper {
    LandPlotResponseDto toDto(LandPlot landPlot);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "area", ignore = true)
    })
    LandPlot toEntity(LandPlotRequestDto landPlotRequestDto);
}
