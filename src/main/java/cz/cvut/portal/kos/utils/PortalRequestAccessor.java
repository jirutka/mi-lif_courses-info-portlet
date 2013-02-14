package cz.cvut.portal.kos.utils;

import javax.portlet.PortletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.portlet.context.PortletRequestAttributes;

/**
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class PortalRequestAccessor {

    public static PortletRequest getCurrentRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

        if (attributes instanceof PortletRequestAttributes) {
            return ((PortletRequestAttributes) attributes).getRequest();
        }
        throw new IllegalStateException("Does not run within a PortletContext");
    }
}
