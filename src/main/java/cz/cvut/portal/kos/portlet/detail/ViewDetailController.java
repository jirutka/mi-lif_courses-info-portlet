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
package cz.cvut.portal.kos.portlet.detail;

import cz.cvut.portal.kos.model.Course;
import cz.cvut.portal.kos.portlet.Constants.A;
import cz.cvut.portal.kos.portlet.Constants.P;
import cz.cvut.portal.kos.portlet.PortletMode;
import cz.cvut.portal.kos.services.KOSapiService;
import javax.portlet.PortletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpClientErrorException;
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

    @ExceptionHandler
    public String handleNotFound(HttpClientErrorException exception, PortletRequest request) {
        request.setAttribute(A.error, exception);

        return "error";
    }

}
