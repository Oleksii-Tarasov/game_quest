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
import java.util.List;

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
        Character character = (Character) req.getSession().getAttribute("character");
        String locationId = req.getParameter("id");
        character.setCurrentLocationId(locationId);

        Location currentLocation = character.getGameLocations().get(locationId);

        req.setAttribute("image", currentLocation.getImage());
        req.setAttribute("sound", currentLocation.getSound());
        req.setAttribute("storyBlock", currentLocation.getStoryBlock());


        List<String> characterInventory = character.getInventory();
        if (characterInventory!=null && !characterInventory.isEmpty()) {
            req.setAttribute("inventory", gameMaster.showItemsInInventory(characterInventory));
            req.setAttribute("showInventory", true);
        }

        List<String> itemsInLocation = currentLocation.getItemsInLocation();
        if (!itemsInLocation.isEmpty()) {
            req.setAttribute("itemsInLocation", gameMaster.showItemsInLocation(itemsInLocation));
        }

        displayEndGameStatusIfItsOver(req, locationId);

        req.getSession().setAttribute("tries", gameMaster.countTries(character));

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void displayEndGameStatusIfItsOver(HttpServletRequest request, String locationId) {
        if (BAD_ENDS.contains(locationId)) {
            request.setAttribute("isGameOver", true);
            request.setAttribute("showInventory" , false);
        } else if (GOOD_ENDS.contains(locationId)) {
            request.setAttribute("isWinner", true);
            request.setAttribute("showInventory" , false);
        }

        if ("firebridge".equals(locationId)) {
            request.setAttribute("showInventory", false);
        }
    }
}
