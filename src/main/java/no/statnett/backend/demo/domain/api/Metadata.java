package no.statnett.backend.demo.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metadata {

    private Long generated;
    private String url;
    private String title;
    private Integer status;
    private String api;
    private Integer count;
}
