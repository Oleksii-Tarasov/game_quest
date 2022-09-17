package ua.com.javarush.escape_quest.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static ua.com.javarush.escape_quest.constant.LocationRules.*;


@WebFilter("/location/")
public class LocationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String locationId = servletRequest.getParameter("id");

        if (DONT_SHOW_INVENTORY_IN_LOCATIONS.contains(locationId)) {
            servletRequest.setAttribute("showInventory", false);
        } else {
            servletRequest.setAttribute("showInventory", true);
        }

        if (BAD_ENDS.contains(locationId)) {
            servletRequest.setAttribute("isGameOver", true);
            servletRequest.setAttribute("showInventory", false);
        } else if (GOOD_ENDS.contains(locationId)) {
            servletRequest.setAttribute("isWinner", true);
            servletRequest.setAttribute("showInventory", false);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
