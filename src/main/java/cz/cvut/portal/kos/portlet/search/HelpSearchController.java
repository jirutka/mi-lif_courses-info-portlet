package cz.cvut.portal.kos.portlet.search;

import cz.cvut.portal.kos.portlet.PortletMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
@Controller
@RequestMapping(PortletMode.HELP)
public class HelpSearchController {

    @RenderMapping
    public String render() {
        return "help";
    }
}
