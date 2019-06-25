package io.iamkyu;

import io.iamkyu.mapping.ShortUrlRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Controller {

    private final AggregateService aggregateService;

    public Controller(AggregateService aggregateService) {
        this.aggregateService = aggregateService;
    }

    @PostMapping(value = "/short-url",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AggregateService.ShortUrlResponse mapUrl(@RequestBody ShortUrlRequest mappingRequest) {
        return aggregateService.mapping(mappingRequest.getUrl());
    }

    @GetMapping("/{key}")
    public RedirectView redirect(@PathVariable String key) {
        String actualUrl = aggregateService.getActualUrl(key);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(actualUrl);
        return redirectView;
    }
}
