package pl.haladyj.jwtspringsecuritydemo.payload;

import lombok.Data;

@Data
public class JwtAuuthenticationResponce {
    private String accessToken;
    private String tokenType = "Bearer";

}
