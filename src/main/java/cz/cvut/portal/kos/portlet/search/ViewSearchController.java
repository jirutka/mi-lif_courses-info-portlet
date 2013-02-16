package cz.cvut.portal.kos.portlet.search;

import cz.cvut.portal.kos.model.Course;
import cz.cvut.portal.kos.services.support.Paginator;
import cz.cvut.portal.kos.services.support.ListPaginator;
import cz.cvut.portal.kos.portlet.Constants.A;
import cz.cvut.portal.kos.portlet.PortletMode;
import cz.cvut.portal.kos.services.KOSapiService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author Jakub Jirutka
 */
@Controller
@RequestMapping(PortletMode.VIEW)
@SessionAttributes(A.paginator)
public class ViewSearchController {

    @Autowired
    private KOSapiService kosapi;


    @RenderMapping
    public String render() {
        return "view";
    }

    @ActionMapping("find")
    public void find(@RequestParam String query, Model model) {
        Paginator<Course> paginator;

        if (StringUtils.isNotBlank(query)) {
            paginator = kosapi.findCoursesByCodeOrName(query);
            paginator.setItemsPerPage(SearchPreferences.load().getItemsPerPage());
        } else {
            paginator = ListPaginator.emptyList();
        }
        model.addAttribute(A.paginator, paginator);
    }

    @ActionMapping("goToPage")
    public void goToPage(@RequestParam int page, @ModelAttribute(A.paginator) Paginator<Course> paginator) {
        paginator.goToPage(page);
    }

    @ModelAttribute(A.paginator)
    public Paginator<Course> populateEmptyPaginator() {
        return ListPaginator.emptyList();
    }
    
}
