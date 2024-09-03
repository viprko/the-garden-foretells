package pet.thegardenforetells.perennialplantservice.service;

import java.util.List;
import java.util.UUID;
import pet.thegardenforetells.perennialplantservice.model.PerennialPlant;

public interface PerennialPlantService {
    PerennialPlant findById(Long id);

    List<PerennialPlant> findAllByUser(UUID userId);

    PerennialPlant save(PerennialPlant perennialPlant);

    PerennialPlant update(PerennialPlant perennialPlant);

    void deleteById(Long id);
}
