package no.statnett.backend.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.statnett.backend.demo.client.USGSEarthquakeClient;
import no.statnett.backend.demo.domain.Earthquake;
import no.statnett.backend.demo.domain.api.EarthQuakeCollection;
import no.statnett.backend.demo.domain.api.Features;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EarthquakeService {

    private final USGSEarthquakeClient earthQuakeClient;

    public List<Earthquake> getEarthquakes() {
        EarthQuakeCollection earthQuakeCollection = earthQuakeClient.getEarthquakes();
        return mapEarthquakeCollectionToEarthquakes(earthQuakeCollection);
    }

    public List<Earthquake> mapEarthquakeCollectionToEarthquakes(EarthQuakeCollection earthQuakeCollection) {
        List<Earthquake> earthquakes = new ArrayList<>();
        for (Features features : earthQuakeCollection.getFeatures()) {
            Earthquake earthquake = new Earthquake();
            earthquake.setId(features.getId());
            earthquake.setTime(features.getProperties().getTime());
            earthquake.setPlace(features.getProperties().getPlace());
            earthquake.setMagnitude(features.getProperties().getMagnitude());
            earthquake.setGeometry(features.getGeometry());
            earthquakes.add(earthquake);
        }
        return earthquakes;
    }
}
