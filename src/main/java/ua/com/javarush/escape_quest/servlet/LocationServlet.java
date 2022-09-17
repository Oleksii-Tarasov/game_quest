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
        req.getSession().setAttribute("currentLocation", locationId);

        Character character = (Character) req.getSession().getAttribute("character");
        Location currentLocation = character.getGameLocations().get(locationId);

        req.setAttribute("image", currentLocation.getImage());
        req.setAttribute("sound", currentLocation.getSound());
        req.setAttribute("storyBlock", currentLocation.getStoryBlock());

        List<String> characterInventory = character.getInventory();
        if (characterInventory != null && !characterInventory.isEmpty()) {
            req.setAttribute("inventory", gameMaster.showItemsInInventory(characterInventory));
        }

        List<String> itemsInLocation = currentLocation.getItemsInLocation();
        if (!itemsInLocation.isEmpty()) {
            req.setAttribute("itemsInLocation", gameMaster.showItemsInLocation(itemsInLocation));
            req.setAttribute("showItems", true);
        }

        req.getSession().setAttribute("tries", gameMaster.countTries(character));

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
