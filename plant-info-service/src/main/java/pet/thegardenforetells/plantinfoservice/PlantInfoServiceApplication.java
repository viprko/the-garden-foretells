package pet.thegardenforetells.plantinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import pet.thegardenforetells.plantinfoservice.model.Tree;

@SpringBootApplication
@EnableDiscoveryClient
public class PlantInfoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlantInfoServiceApplication.class, args);
    }
}
