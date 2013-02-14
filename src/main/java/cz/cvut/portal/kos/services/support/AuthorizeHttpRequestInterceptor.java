package cz.cvut.portal.kos.services.support;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Intercept HTTP request and add header for HTTP Basic authorization with
 * given credentials.
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class AuthorizeHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    
    private static final Logger LOG = LoggerFactory.getLogger(AuthorizeHttpRequestInterceptor.class);
    private static final Charset ASCII_CHARSET = Charset.forName("US-ASCII");

    private String username;
    private String password;


    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String value = createBasicAuthHeader();

        LOG.debug("Adding header Authorization: {}", value);
        request.getHeaders().set("Authorization", value);

        return execution.execute(request, body);
    }

    private String createBasicAuthHeader() {
        String credentials = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(credentials.getBytes(ASCII_CHARSET));

        return "Basic " + new String(encoded);
    }


    ////////  Accessors  ////////

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
