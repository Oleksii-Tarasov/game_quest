package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.Character;
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
        String locationId = req.getParameter("id");
        Character character = (Character) req.getSession().getAttribute("character");

        Location currentLocation = gameMaster.getLocationForCurrentCharacter(character, locationId);

        character.setCurrentLocationId(locationId);
        displayEndGameStatusIfItsOver(req, character, locationId);

        req.setAttribute("image", currentLocation.getImage());
        req.setAttribute("sound", currentLocation.getSound());
        req.setAttribute("storyBlock", currentLocation.getStoryBlock());
        req.setAttribute("nickname", character.getNickname());
        req.setAttribute("inventory", gameMaster.showCharacterInventory(character));
        req.setAttribute("itemsInLocation", gameMaster.showItemsInLocation(currentLocation));
        req.getSession().setAttribute("tries", gameMaster.countTries(character));

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void displayEndGameStatusIfItsOver(HttpServletRequest request, Character character, String locationId) {
        if (BAD_ENDS.contains(locationId)) {
            request.setAttribute("isGameOver", true);
            gameMaster.dontShowCharacterInventory(character);
        } else if (GOOD_ENDS.contains(locationId)) {
            request.setAttribute("isWinner", true);
            gameMaster.dontShowCharacterInventory(character);
        }
    }
}
