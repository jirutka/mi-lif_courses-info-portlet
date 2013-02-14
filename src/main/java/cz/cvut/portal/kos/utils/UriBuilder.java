package cz.cvut.portal.kos.utils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class UriBuilder {

    private Map<String, String> variables = new HashMap<String, String>();
    private String base;
    private UriComponentsBuilder builder = UriComponentsBuilder.newInstance();


    private UriBuilder(String baseUri) {
        this.base = baseUri;
    }

    public static UriBuilder fromBase(String baseUri) {
        return new UriBuilder(baseUri);
    }

    public UriBuilder path(String path) {
        this.builder = UriComponentsBuilder.fromUriString(path);
        return this;
    }

    public UriBuilder expand(String variable, String value) {
        variables.put(variable, value);
        return this;
    }

    public UriBuilder query(String query) {
        builder.query(query);
        return this;
    }

    public UriBuilder queryParam(String name, Object... values) {
        builder.queryParam(name, values);
        return this;
    }

    public URI build() {
        URI path = builder.buildAndExpand(variables).toUri();
        return path.isAbsolute() ? path : URI.create(base + path);
    }

}
