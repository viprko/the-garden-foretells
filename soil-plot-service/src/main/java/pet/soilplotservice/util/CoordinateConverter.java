package pet.soilplotservice.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pet.soilplotservice.exception.CoordinateConverterException;
import pet.soilplotservice.model.Coordinate;

@Component
@Converter
@RequiredArgsConstructor
public class CoordinateConverter implements AttributeConverter<List<Coordinate>, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<Coordinate> coordinates) {
        try {
            return objectMapper.writeValueAsString(coordinates);
        } catch (Exception e) {
            throw new CoordinateConverterException("Failed to convert lis of coordinates to "
                    + "JSON", e);
        }
    }

    @Override
    public List<Coordinate> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<List<Coordinate>>() {
            });
        } catch (Exception e) {
            throw new CoordinateConverterException("Failed to convert JSON to list of "
                    + "coordinates", e);
        }
    }
}
