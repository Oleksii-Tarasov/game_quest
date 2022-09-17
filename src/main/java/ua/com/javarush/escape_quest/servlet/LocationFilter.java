package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.javarush.escape_quest.constant.LocationRules.*;


@WebFilter("/location/")
public class LocationFilter implements Filter {
    private GameMaster gameMaster;

    @Override
    public void init(FilterConfig filterConfig) {
        gameMaster = (GameMaster) filterConfig.getServletContext().getAttribute("gameMaster");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String locationId = servletRequest.getParameter("id");

        if (DONT_SHOW_INVENTORY_IN_LOCATIONS.contains(locationId)) {
            servletRequest.setAttribute("showInventory", false);
        } else {
            servletRequest.setAttribute("showInventory", true);
        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Character character = (Character) session.getAttribute("character");

        if (BAD_ENDS.contains(locationId)) {
            servletRequest.setAttribute("isGameOver", true);
            servletRequest.setAttribute("showInventory", false);

            gameMaster.calculateStatistics(character, "badEnd");
        } else if (GOOD_ENDS.contains(locationId)) {
            servletRequest.setAttribute("isWinner", true);
            servletRequest.setAttribute("showInventory", false);

            gameMaster.calculateStatistics(character, "goodEnd");
        }

        session.setAttribute("badTries", character.getBadEndsNumber());
        session.setAttribute("goodTries", character.getGoodEndsNumber());
        session.setAttribute("gameTries", character.getGameAttempt());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
