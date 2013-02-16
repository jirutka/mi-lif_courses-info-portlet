/*
 * The MIT License
 *
 * Copyright 2013 Jakub Jirutka <jakub@jirutka.cz>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
