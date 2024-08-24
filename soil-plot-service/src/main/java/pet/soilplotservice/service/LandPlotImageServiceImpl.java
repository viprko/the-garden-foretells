package pet.soilplotservice.service;

import jakarta.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pet.soilplotservice.exception.ImageDirectoryCreationException;
import pet.soilplotservice.model.LandPlot;
import pet.soilplotservice.util.LandPlotImageGenerator;

@Service
@RequiredArgsConstructor
@Slf4j
public class LandPlotImageServiceImpl implements LandPlotImageService {
    private final LandPlotImageGenerator landPlotImageGenerator;
    private Path imageDirectoryPath;


    @PostConstruct
    public void init() {
        try {
            Path rootDir = Paths.get("").toAbsolutePath();
            imageDirectoryPath = rootDir.resolve("images");
            if (!Files.exists(imageDirectoryPath)) {
                Files.createDirectory(imageDirectoryPath);
                log.info("Created directory for storing land plots images: {}", imageDirectoryPath);
            }
        } catch (IOException e) {
            throw new ImageDirectoryCreationException("Failed to create a directory for land plot"
                    + " image storing", e);
        }
    }

    @Override
    public void saveImageFile(LandPlot landPlot) throws IOException {
        BufferedImage landPlotImage = landPlotImageGenerator.generateImage(landPlot.getVertices());
        File file = new File(imageDirectoryPath.toFile(), generateImageName(landPlot.getId()));
        ImageIO.write(landPlotImage, "PNG", file);
    }

    private String generateImageName(Long landPlotId) {
        return String.format("%d:id-landplot", landPlotId);
    }

}
