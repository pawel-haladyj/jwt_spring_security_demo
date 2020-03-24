package pl.haladyj.jwtspringsecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		JwtSpringSecurityDemoApplication.class,
		Jsr310JpaConverters.class
})
public class JwtSpringSecurityDemoApplication {

	void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecurityDemoApplication.class, args);
	}

}
