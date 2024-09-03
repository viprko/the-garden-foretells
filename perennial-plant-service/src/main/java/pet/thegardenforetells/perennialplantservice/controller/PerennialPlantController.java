package pet.thegardenforetells.perennialplantservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plants/perennial")
public class PerennialPlantController {

    @GetMapping("/{id}")
    public PerennialPlantResponseDto findById(@PathVariable("id") Long id) {
        return
    }

}
