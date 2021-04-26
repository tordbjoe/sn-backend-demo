package no.statnett.backend.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.statnett.backend.demo.domain.api.Geometry;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Earthquake {
    private String id;
    private String place;
    private Double magnitude;
    private Long time;
    private Geometry geometry;
}
