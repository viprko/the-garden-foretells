package pet.thegardenforetells.plantinfoservice.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.thegardenforetells.plantinfoservice.dto.TreeRequestDto;
import pet.thegardenforetells.plantinfoservice.dto.TreeResponseDto;
import pet.thegardenforetells.plantinfoservice.service.TreeService;
import pet.thegardenforetells.plantinfoservice.service.mapper.TreeMapper;

@RestController
@RequestMapping("/trees")
@RequiredArgsConstructor
public class TreeController {
    private final TreeService treeService;
    private final TreeMapper treeMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TreeResponseDto getById(@PathVariable("id") Long id) {
        return treeMapper.toDto(treeService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TreeResponseDto save(@RequestBody TreeRequestDto treeRequestDto) {
        return treeMapper.toDto(treeService.save(treeMapper.toEntity(treeRequestDto)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TreeResponseDto update(@PathVariable("id") Long id,
                                  @RequestBody TreeRequestDto treeRequestDto) {
        return treeMapper.toDto(treeService.update(id, treeMapper.toEntity(treeRequestDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        treeService.deleteById(id);
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public List<TreeResponseDto> findBy(@RequestBody TreeRequestDto treeRequestDto) {
        return treeService.findBy(treeMapper.toEntity(treeRequestDto)).stream()
                .map(treeMapper::toDto)
                .toList();
    }
}
