package es.codeurjc.daw;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.codeurjc.daw.dto.FullPostDTO;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public ModelMapper modelMapper() {

	    ModelMapper modelMapper = new ModelMapper();

        PropertyMap<Post, FullPostDTO> personMap = new PropertyMap<Post, FullPostDTO>() {
            protected void configure() {
                map().setComments(source.getComments());
            }
        };

        modelMapper.addMappings(personMap);

        return modelMapper;
    }
}