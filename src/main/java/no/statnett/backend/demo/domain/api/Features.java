package no.statnett.backend.demo.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Features {

    private String id;
    private String type;
    private Properties properties;
    private Geometry geometry;
}
