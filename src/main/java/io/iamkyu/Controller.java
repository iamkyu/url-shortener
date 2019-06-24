package io.iamkyu;

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

    @PostMapping(value = "/mapping",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AggregateService.ShortUrlResponse mapUrl(@RequestBody String url) {
        return aggregateService.mapping(url);
    }

    @GetMapping("/{key}")
    public RedirectView redirect(@PathVariable String key) {
        // FIXME url 필드에 json 형태로 저장되어 있음.
        String actualUrl = aggregateService.getActualUrl(key);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(actualUrl);
        return redirectView;
    }
}
