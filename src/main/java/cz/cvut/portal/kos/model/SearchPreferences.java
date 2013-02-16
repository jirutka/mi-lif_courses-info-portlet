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
package cz.cvut.portal.kos.model;

import cz.cvut.portal.kos.utils.PortalRequestAccessor;
import java.io.IOException;
import java.io.Serializable;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Represents {@link PortletPreferences}
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class SearchPreferences implements Serializable {

    @Min(1) @Max(20)
    private Integer itemsPerPage;


    protected SearchPreferences() {
    }
    protected SearchPreferences(PortletPreferences preferences) {
        itemsPerPage = Integer.valueOf(preferences.getValue("itemsPerPage", null));
    }
    
    
    public static SearchPreferences load() {
        return new SearchPreferences(getPortletPreferences());
    }
    
    public void save() {
        PortletPreferences pref = getPortletPreferences();
        try {
            pref.setValue("itemsPerPage", itemsPerPage.toString());
            pref.store();
            
        // multi-catch in Java 7 is pretty cool, huh? ;)
        } catch (IOException | ValidatorException | ReadOnlyException ex) {
            throw new RuntimeException(ex);
        }
    }


    private static PortletPreferences getPortletPreferences() {
        return PortalRequestAccessor.getCurrentRequest().getPreferences();
    }

    
    ////////  Accessors  ////////

    public Integer getItemsPerPage() { return itemsPerPage; }
    public void setItemsPerPage(Integer itemsPerPage) { this.itemsPerPage = itemsPerPage; }

}
