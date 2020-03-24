package pl.haladyj.jwtspringsecuritydemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppiResponse {

    private Boolean success;
    private String message;

}
