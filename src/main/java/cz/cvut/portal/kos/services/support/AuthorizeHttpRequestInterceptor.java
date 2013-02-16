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
