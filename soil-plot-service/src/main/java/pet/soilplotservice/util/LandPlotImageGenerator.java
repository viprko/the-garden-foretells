package pet.soilplotservice.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;
import pet.soilplotservice.model.Coordinate;


@Component
public class LandPlotImageGenerator {
    public BufferedImage generateImage(List<Coordinate> vertices) throws IOException {
        int width = 800;
        int height = 600;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(java.awt.Color.BLACK);
        g2d.setStroke(new java.awt.BasicStroke(2));
        for (int i = 0; i < vertices.size(); i++) {
            Coordinate start = vertices.get(i);
            Coordinate end = vertices.get((i + 1) % vertices.size());
            g2d.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
        }
        g2d.dispose();
        return image;
    }
}

