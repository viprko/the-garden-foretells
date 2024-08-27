package pet.soilplotservice.controller;

import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.soilplotservice.annotation.UserId;
import pet.soilplotservice.dto.LandPlotRequestDto;
import pet.soilplotservice.dto.LandPlotResponseDto;
import pet.soilplotservice.service.LandPlotService;
import pet.soilplotservice.service.mapper.LandPlotMapper;

@RestController
@RequiredArgsConstructor
public class LandPlotController {
    private final LandPlotService landPlotService;
    private final LandPlotMapper landPlotMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LandPlotResponseDto findById(@PathVariable("id") Long id) {
        return landPlotMapper.toDto(landPlotService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LandPlotResponseDto> findAllByUser(@UserId String userId) {
        return landPlotService.findAllByUser(userId)
                .stream()
                .map(landPlotMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LandPlotResponseDto save(@RequestBody LandPlotRequestDto landPlotRequestDto,
                                    @Parameter(hidden = true) @UserId String userId) {
        return landPlotMapper.toDto(
                landPlotService.save(landPlotMapper.toEntity(landPlotRequestDto), userId));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LandPlotResponseDto update(@RequestBody LandPlotRequestDto landPlotRequestDto,
                                      @PathVariable("id") Long id) {
        return landPlotMapper.toDto(landPlotService.update(id,
                landPlotMapper.toEntity(landPlotRequestDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        landPlotService.delete(id);
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck () {
        return new ResponseEntity<>("Auth service is up", HttpStatus.OK);
    }
}
