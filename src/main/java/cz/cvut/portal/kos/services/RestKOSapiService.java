package cz.cvut.portal.kos.services;

import cz.cvut.portal.kos.model.Course;
import cz.cvut.portal.kos.services.support.ListPaginator;
import cz.cvut.portal.kos.services.support.Paginator;
import cz.cvut.portal.kos.services.support.ListPaginator.DataFetcher;
import cz.cvut.portal.kos.utils.UriBuilder;
import cz.jirutka.atom.jaxb.Entry;
import cz.jirutka.atom.jaxb.Feed;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class RestKOSapiService implements KOSapiService {

    private static final String PARAM_LIMIT = "limit";
    private static final String PARAM_OFFSET = "offset";

    private RestTemplate rest;

    // will be set from properties file
    private @Value("${kosapi.uri.base}") String baseUri;
    private @Value("${kosapi.uri.courses.query-simple}") String coursesQuerySimpleUri;
    private @Value("${kosapi.uri.courses.query-rsql}") String coursesQueryRsqlUri;
    private @Value("${kosapi.uri.courses.get}") String coursesGetUri;


    
    public Paginator<Course> findCoursesByCodeOrName(final String codeOrName) {
        return new ListPaginator<>(new DataFetcher<Course>() {

            public List<Course> fetchPage(int itemsPerPage, int startIndex) {
                URI url = path(coursesQuerySimpleUri)
                            .expand("query", codeOrName)
                            .queryParam(PARAM_LIMIT, itemsPerPage)
                            .queryParam(PARAM_OFFSET, startIndex)
                            .build();
                
                return rest.getForObject(url, Feed.class).getContents();
            }
        });
    }

    public Course getCourse(String code) {
        Entry<Course> entry = rest.getForObject(path(coursesGetUri).expand("code", code).build(), Entry.class);

        return entry != null ? entry.getContent() : null;
    }

    private UriBuilder path(String path) {
        return UriBuilder.fromBase(baseUri).path(path);
    }


    ////////  Accessors  ////////

    public void setRestTemplate(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

}
