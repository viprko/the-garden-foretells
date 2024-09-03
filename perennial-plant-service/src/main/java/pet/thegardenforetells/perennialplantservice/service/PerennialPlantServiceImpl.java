package pet.thegardenforetells.perennialplantservice.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.thegardenforetells.perennialplantservice.exception.PerennialPlantNotFoundException;
import pet.thegardenforetells.perennialplantservice.model.PerennialPlant;
import pet.thegardenforetells.perennialplantservice.repository.PerennialPlantRepository;

@Service
@RequiredArgsConstructor
public class PerennialPlantServiceImpl implements PerennialPlantService {
    private final PerennialPlantRepository perennialPlantRepository;

    @Override
    public PerennialPlant findById(Long id) {
        return perennialPlantRepository.findById(id).orElseThrow(
                () -> new PerennialPlantNotFoundException(
                        String.format("Perennial plant with id: %d was not found", id)));
    }

    @Override
    public List<PerennialPlant> findAllByUser(UUID userId) {
        return perennialPlantRepository.findAllByUser(userId);
    }

    @Override
    @Transactional
    public PerennialPlant save(PerennialPlant perennialPlant) {
        return perennialPlantRepository.save(perennialPlant);
    }

    @Override
    @Transactional
    public PerennialPlant update(PerennialPlant perennialPlant) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
