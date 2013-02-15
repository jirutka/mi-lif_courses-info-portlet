package cz.cvut.portal.kos.portlet.search;

import cz.cvut.portal.kos.portlet.Constants.A;
import cz.cvut.portal.kos.portlet.PortletMode;
import cz.cvut.portal.kos.services.KOSapiService;
import cz.jirutka.atom.jaxb.Entry;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@SessionAttributes(A.entries)
public class ViewSearchController {

    @Autowired
    private KOSapiService kosapi;


    @RenderMapping
    public String render() {
        return "view";
    }

    @ActionMapping
    public void find(@RequestParam String query, @ModelAttribute(A.entries) List<Entry> entries) {
        entries.clear();

        if (StringUtils.isNotBlank(query)) {
            entries.addAll(kosapi.findCoursesByCodeOrName(query));
        }
    }

    @ModelAttribute(A.entries)
    public List<Entry> populateDefaultEntries() {
        return new ArrayList<Entry>();
    }

}
