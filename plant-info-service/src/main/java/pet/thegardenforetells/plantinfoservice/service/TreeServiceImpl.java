package pet.thegardenforetells.plantinfoservice.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.thegardenforetells.plantinfoservice.exception.TreeNotFoundException;
import pet.thegardenforetells.plantinfoservice.model.Tree;
import pet.thegardenforetells.plantinfoservice.repository.TreeRepository;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {
    private final TreeRepository treeRepository;

    @Override
    @Transactional
    public Tree save(Tree tree) {
        if (tree != null) {
            return treeRepository.save(tree);
        }
        throw new IllegalArgumentException("Saved tree couldn't be null");
    }

    @Override
    public Tree findById(Long id) {
        return treeRepository.findById(id)
                .orElseThrow(() -> new TreeNotFoundException(String.format(
                        "Tree with id: %d not found", id)));
    }

    @Override
    public List<Tree> findBy(Tree tree) {
        return List.of();
    }

    @Override
    @Transactional
    public Tree update(Long id, Tree tree) {
        Tree savedTree = treeRepository.findById(id)
                .orElseThrow(() -> new TreeNotFoundException(String.format(
                        "Tree with id: %d not found", id)));
        Optional.ofNullable(tree.getTreeType()).ifPresent(savedTree::setTreeType);
        Optional.ofNullable(tree.getDescription()).ifPresent(savedTree::setDescription);
        Optional.ofNullable(tree.getFamily()).ifPresent(savedTree::setFamily);
        Optional.ofNullable(tree.getName()).ifPresent(savedTree::setName);
        Optional.ofNullable(tree.getMaxHeightCm()).ifPresent(savedTree::setMaxHeightCm);
        Optional.ofNullable(tree.getMinHeightCm()).ifPresent(savedTree::setMinHeightCm);
        Optional.ofNullable(tree.getSoilTypes()).ifPresent(savedTree::setSoilTypes);
        Optional.ofNullable(tree.getTemperatureMaxC()).ifPresent(savedTree::setTemperatureMaxC);
        Optional.ofNullable(tree.getTemperatureMinC()).ifPresent(savedTree::setTemperatureMinC);
        return treeRepository.save(savedTree);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
