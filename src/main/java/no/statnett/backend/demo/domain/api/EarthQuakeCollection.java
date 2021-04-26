package no.statnett.backend.demo.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EarthQuakeCollection {

    private String type;
    private Metadata metadata;
    private List<Features> features = new ArrayList<>();

}
