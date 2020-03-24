package pl.haladyj.jwtspringsecuritydemo.payload;

import lombok.Data;
import pl.haladyj.jwtspringsecuritydemo.security.JwtAuthenticationFilter;

@Data
public class JwtAuthenticationResponce {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponce(String accessToken){
        this.accessToken = accessToken;
    }
}
