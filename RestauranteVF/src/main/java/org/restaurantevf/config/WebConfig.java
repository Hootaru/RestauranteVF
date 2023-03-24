package org.restaurantevf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${empleosapp.ruta.imagenes}")
	private String rutaImagenes;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//Prueba
		// Configuración de los recursos estáticos (imagenes de las vacantes) 
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/"); // Windows
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/"); // Linux
		registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagenes); 
		
	}
	
}
