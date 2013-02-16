package cz.cvut.portal.kos.services;

import cz.cvut.portal.kos.model.Course;
import cz.cvut.portal.kos.services.support.Paginator;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public interface KOSapiService {

    Paginator<Course> findCoursesByCodeOrName(String codeOrName);

    Course getCourse(String code);

}
