package no.statnett.backend.demo;

import com.google.common.io.Resources;
import no.statnett.backend.demo.domain.Earthquake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.DefaultUriBuilderFactory;
import wiremock.com.google.common.base.Charsets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWireMock
@ActiveProfiles("integration")
public class IntegrationTest extends TestBase {

    @Autowired
    private TestRestTemplate restTemplate;

    String usgsResponseMock;

    @BeforeEach
    public void setup() throws IOException {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
        usgsResponseMock = Resources.toString(Resources.getResource("json/mockresponse.json"), Charsets.UTF_8);
        stubFor(get(urlPathEqualTo("/earthquakes/feed/v1.0/summary/1.0_hour.geojson"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(usgsResponseMock)
                ));
    }

    @Test
    public void simpleControllerTest() {
        ResponseEntity<Earthquake[]> responseEntity = restTemplate.getForEntity("/statnett/backend/api/earthquakes", Earthquake[].class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Earthquake> earthquakes = Arrays.asList(responseEntity.getBody());
        assertThat(earthquakes).isNotEmpty();
        assertThat(earthquakes).hasSize(6);
    }

}
