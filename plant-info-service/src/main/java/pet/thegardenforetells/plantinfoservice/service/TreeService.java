package pet.thegardenforetells.plantinfoservice.service;

import java.util.List;
import pet.thegardenforetells.plantinfoservice.model.Tree;

public interface TreeService {
     Tree save(Tree tree);
     Tree findById(Long id);
     List<Tree> findBy(Tree tree);
     Tree update(Long id, Tree tree);
     void deleteById(Long id);
}
