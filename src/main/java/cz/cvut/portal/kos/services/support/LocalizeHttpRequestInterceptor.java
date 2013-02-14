package cz.cvut.portal.kos.services.support;

import cz.cvut.portal.kos.utils.PortalRequestAccessor;
import java.io.IOException;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Intercept HTTP request and add Accept-Language header with language
 * according to locale from PortletRequest (i.e. current user's locale).
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class LocalizeHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LocalizeHttpRequestInterceptor.class);


    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String lang = currentLocale().getLanguage();

        LOG.debug("Adding header Accept-Language: {}", lang);
        request.getHeaders().set("Accept-Language", lang);

        return execution.execute(request, body);
    }

    private Locale currentLocale() {
        return PortalRequestAccessor.getCurrentRequest().getLocale();
    }

}
