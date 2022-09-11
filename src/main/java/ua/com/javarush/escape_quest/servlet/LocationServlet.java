package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.Location;
import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.com.javarush.escape_quest.constant.GoodBadEnds.BAD_ENDS;
import static ua.com.javarush.escape_quest.constant.GoodBadEnds.GOOD_ENDS;

@WebServlet("/location/")
public class LocationServlet extends HttpServlet {
    private GameMaster gameMaster;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameMaster = (GameMaster) config.getServletContext().getAttribute("gameMaster");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locationTitle = req.getParameter("title");

        Location currentLocation = gameMaster.getGameLocations().get(locationTitle);

        displayEndGameStatusIfItsOver(req, locationTitle);

        req.setAttribute("image", currentLocation.getImage());
        req.setAttribute("sound", currentLocation.getSound());
        req.setAttribute("storyBlock", currentLocation.getStoryBlock());
        req.setAttribute("nickname", gameMaster.getCharacter().getNickname());
        req.setAttribute("inventory", gameMaster.showCharacterInventory());
        req.setAttribute("itemsInLocation", gameMaster.showItemsInLocation(locationTitle));
        req.getSession().setAttribute("tries", gameMaster.countTries());

        gameMaster.rememberCurrentLocation(locationTitle);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void displayEndGameStatusIfItsOver(HttpServletRequest request, String locationTitle) {
        if (BAD_ENDS.contains(locationTitle)) {
            request.setAttribute("isGameOver", true);
            gameMaster.dontShowCharacterInventory();
        } else if (GOOD_ENDS.contains(locationTitle)) {
            request.setAttribute("isWinner", true);
            gameMaster.dontShowCharacterInventory();
        }
    }
}
