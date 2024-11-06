package pet.thegardenforetells.plantinfoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.thegardenforetells.plantinfoservice.model.Tree;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
}
