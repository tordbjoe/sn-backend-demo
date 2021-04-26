package no.statnett.backend.demo.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Properties {
    @JsonProperty("mag")
    private Double magnitude;
    private String place;
    private Long time;
    private Long updated;
    private String type;

}
