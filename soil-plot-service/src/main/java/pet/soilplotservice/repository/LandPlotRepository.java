package pet.soilplotservice.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.soilplotservice.model.LandPlot;

@Repository
public interface LandPlotRepository extends JpaRepository<LandPlot, Long> {

    @Query("SELECT lp FROM LandPlot lp WHERE lp.userId = :userId")
    public List<LandPlot> findAllByUser(@Param("userId") UUID userId);
}
