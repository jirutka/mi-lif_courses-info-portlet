package cz.cvut.portal.kos.portlet.search;

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
public class Preferences implements Serializable {

    @Min(1) @Max(20)
    private Integer itemsPerPage;


    protected Preferences() {
    }
    protected Preferences(PortletPreferences preferences) {
        itemsPerPage = Integer.valueOf(preferences.getValue("itemsPerPage", null));
    }
    
    
    public static Preferences load() {
        return new Preferences(getPortletPreferences());
    }
    
    public void save() {
        PortletPreferences pref = getPortletPreferences();
        try {
            pref.setValue("itemsPerPage", itemsPerPage.toString());
            System.out.println("Saving " + itemsPerPage);
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
