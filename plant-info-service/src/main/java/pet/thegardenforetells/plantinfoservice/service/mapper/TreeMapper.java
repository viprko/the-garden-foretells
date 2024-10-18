package pet.thegardenforetells.plantinfoservice.service.mapper;

import org.mapstruct.Mapper;
import pet.thegardenforetells.plantinfoservice.config.MapperConfig;
import pet.thegardenforetells.plantinfoservice.dto.TreeRequestDto;
import pet.thegardenforetells.plantinfoservice.dto.TreeResponseDto;
import pet.thegardenforetells.plantinfoservice.model.Tree;

@Mapper(config = MapperConfig.class)
public interface TreeMapper {
    TreeResponseDto toDto(Tree tree);

    Tree toEntity(TreeRequestDto treeRequestDto);
}
