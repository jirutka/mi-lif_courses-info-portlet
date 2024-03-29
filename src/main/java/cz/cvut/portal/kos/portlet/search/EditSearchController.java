/*
 * The MIT License
 *
 * Copyright 2013 Jakub Jirutka <jakub@jirutka.cz>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cz.cvut.portal.kos.portlet.search;

import cz.cvut.portal.kos.model.SearchPreferences;
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
public class EditSearchController {

    @RenderMapping
    public String render(Model model) {
        model.addAttribute(A.pref, SearchPreferences.load());

        return "edit";
    }

    @ActionMapping
    public void update(@Valid SearchPreferences preferences, BindingResult result, Model model) {
        if (! result.hasErrors()) {
            preferences.save();
        }
    }
}
