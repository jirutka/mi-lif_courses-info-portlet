package cz.cvut.portal.kos.services.support;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Intercept HTTP request and log its method and URI.
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class LogHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    
    private static final Logger LOG = LoggerFactory.getLogger(LogHttpRequestInterceptor.class);


    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        LOG.info("{} {}", request.getMethod(), request.getURI());

        return execution.execute(request, body);
    }
}
