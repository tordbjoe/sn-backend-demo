package no.statnett.backend.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.statnett.backend.demo.domain.Earthquake;
import no.statnett.backend.demo.service.EarthquakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EarthquakeController {

    private final EarthquakeService earthquakeService;

    @GetMapping(value = "api/earthquakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Earthquake>> getEarthquakes() {
            log.debug("incoming request");
            List<Earthquake> response = earthquakeService.getEarthquakes();
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
