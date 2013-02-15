package cz.cvut.portal.kos.portlet.detail;

import cz.cvut.portal.kos.model.Course;
import cz.cvut.portal.kos.portlet.Constants.A;
import cz.cvut.portal.kos.portlet.Constants.P;
import cz.cvut.portal.kos.portlet.PortletMode;
import cz.cvut.portal.kos.services.KOSapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
@Controller
@RequestMapping(PortletMode.VIEW)
@SessionAttributes(A.course)
public class ViewDetailController {

    private static final Course NULL_COURSE = new Course();

    @Autowired
    private KOSapiService kosapi;

    
    @RenderMapping
    public String renderDefault() {
        return "view";
    }

    @RenderMapping(params=P.course_code)
    public String renderShow(@RequestParam(value=P.course_code) String code, Model model) {
        model.addAttribute(A.course, kosapi.getCourse(code));

        return "view";
    }

    @ModelAttribute(A.course)
    public Course populateDefaultCourse() {
        return NULL_COURSE;
    }

}
