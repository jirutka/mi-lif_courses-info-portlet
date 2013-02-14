package cz.cvut.portal.kos.services;

import cz.cvut.portal.kos.model.Course;
import cz.cvut.portal.kos.utils.UriBuilder;
import cz.jirutka.atom.jaxb.Entry;
import cz.jirutka.atom.jaxb.Feed;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class RestKOSapiService implements KOSapiService {

    private RestTemplate rest;

    // will be set from properties file
    private @Value("${kosapi.uri.base}") String baseUri;
    private @Value("${kosapi.uri.courses.query-simple}") String coursesQuerySimpleUri;
    private @Value("${kosapi.uri.courses.query-rsql}") String coursesQueryRsqlUri;
    private @Value("${kosapi.uri.courses.get}") String coursesGetUri;


    
    public List<Entry<Course>> findCoursesByCodeOrName(String codeOrName) {
        Feed<Course> feed = rest.getForObject(path(coursesQuerySimpleUri).expand("query", codeOrName).build(), Feed.class);

        return feed.getEntries();
    }

    public List<Entry<Course>> findCoursesWithRSQL(String query) {
        Feed<Course> feed = rest.getForObject(path(coursesQueryRsqlUri).expand("query", query).build(), Feed.class);

        return feed.getEntries();
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
