package pl.agh.shopping.card.application.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.agh.shopping.card.application.rest.url.URLProvider;

@Component
@RequiredArgsConstructor
public class RestClient {

    private final RestTemplate restTemplate;
    private final URLProvider urlProvider;

    public <T> T get(MicroService ms, String url, Class<T> type) {
        String baseURL = urlProvider.getBaseURL(ms);
        return restTemplate.getForObject(baseURL + url, type);
    }
}
