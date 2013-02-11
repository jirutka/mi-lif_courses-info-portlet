package cz.cvut.portal.kos.portlet.search;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author Jakub Jirutka
 */
@Controller
@RequestMapping("VIEW")
public class SearchController {

    @RenderMapping
    public String home(Locale locale, Model model) {
        List<String> items = Arrays.asList("First item", "Second item", "Third item");
        model.addAttribute("items", items);

        return "search";
    }

    @ActionMapping(value="find")
    public void find(ActionRequest request, ActionResponse response) {
        System.out.println("Called find action with parameter: " + request.getParameter("query"));
    }
}
