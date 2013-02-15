package cz.cvut.portal.kos.portlet.search;

import cz.cvut.portal.kos.portlet.Constants.A;
import cz.cvut.portal.kos.portlet.PortletMode;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;


/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
@Controller
@RequestMapping(PortletMode.EDIT)
public class EditController {

    @RenderMapping
    public String render(Model model) {
        model.addAttribute(A.pref, Preferences.load());

        return "edit";
    }

    @ActionMapping
    public void update(@Valid Preferences preferences, BindingResult result, Model model) {
        if (! result.hasErrors()) {
            preferences.save();
        }
    }
}
