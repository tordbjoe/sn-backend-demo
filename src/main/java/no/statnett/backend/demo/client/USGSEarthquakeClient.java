package no.statnett.backend.demo.client;

import lombok.extern.slf4j.Slf4j;
import no.statnett.backend.demo.domain.api.EarthQuakeCollection;
import no.statnett.backend.demo.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class USGSEarthquakeClient {

    private final RestTemplate restTemplate;
    private String host;

    public USGSEarthquakeClient(RestTemplate restTemplate, @Value("${endpoint.usgs}") String host) {
        this.restTemplate = restTemplate;
        this.host = host;
    }

    public EarthQuakeCollection getEarthquakes() {
        try {
            String url = getUrl();
            log.info("calling with url: {}", url);
            return restTemplate.getForObject(url, EarthQuakeCollection.class);
        } catch (RestClientException rce) {
            log.error("An error occurred when trying to fetch data : {}", rce.getMessage() );
            throw new ApiException("An error occurred when trying to fetch data from usgs, please try again in a minute");
        } catch (Exception e ) {
            log.error("An error occurred when trying to fetch data : {}", e.getMessage() );
            throw e;
        }
    }

    private String getUrl() {
        String url = UriComponentsBuilder.fromUriString(host)
                .pathSegment("earthquakes", "feed", "v1.0", "summary", "1.0_hour.geojson").build().toString();
        return url;
    }
}
