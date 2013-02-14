package cz.cvut.portal.kos.services;

import cz.cvut.portal.kos.model.Course;
import cz.jirutka.atom.jaxb.Entry;
import java.util.List;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public interface KOSapiService {

    List<Entry<Course>> findCoursesByCodeOrName(String codeOrName);
    
    List<Entry<Course>> findCoursesWithRSQL(String query);

    Course getCourse(String code);

}
