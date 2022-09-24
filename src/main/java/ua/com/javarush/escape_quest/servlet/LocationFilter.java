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
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String locationId = req.getParameter("id");

        if (DONT_DISPLAY_INVENTORY_IN_LOCATIONS.contains(locationId)) {
            req.setAttribute("showInventory", false);
        } else {
            req.setAttribute("showInventory", true);
        }

        HttpSession session = req.getSession();
        Character character = (Character) session.getAttribute("character");

        GameMaster gameMaster = (GameMaster) req.getServletContext().getAttribute("gameMaster");

        if (BAD_ENDS.contains(locationId)) {
            character.setWinner(false);
            gameMaster.calculateStatistics(character);

            req.setAttribute("isGameOver", true);
            req.setAttribute("showInventory", false);
            session.setAttribute("badTries", character.getBadEndsNumber());
        } else if (GOOD_ENDS.contains(locationId)) {
            character.setWinner(true);
            gameMaster.calculateStatistics(character);

            req.setAttribute("isWinner", true);
            req.setAttribute("showInventory", false);
            session.setAttribute("goodTries", character.getGoodEndsNumber());
        }

        req.setAttribute("gameTries", character.getGameAttempt());
        session.setAttribute("battleTries", gameMaster.countBattleTries(character));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
