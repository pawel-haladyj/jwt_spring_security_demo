package pl.haladyj.jwtspringsecuritydemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {

    private Boolean success;
    private String message;

}
