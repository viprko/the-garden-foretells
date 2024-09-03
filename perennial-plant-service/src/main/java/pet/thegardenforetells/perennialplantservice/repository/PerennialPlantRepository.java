package pet.thegardenforetells.perennialplantservice.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.thegardenforetells.perennialplantservice.model.PerennialPlant;

@Repository
public interface PerennialPlantRepository extends JpaRepository<PerennialPlant, Long> {

    @Query("SELECT pp FROM PerennialPlant pp WHERE pp.userId = :userId")
    public List<PerennialPlant> findAllByUser(@Param("userId") UUID userId);
}
