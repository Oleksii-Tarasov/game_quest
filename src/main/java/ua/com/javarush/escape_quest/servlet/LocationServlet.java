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
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();

        Character character = (Character) session.getAttribute("character");

        String locationId = req.getParameter("id");
        character.setCurrentLocationId(locationId);

        Location currentLocation = gameMaster.getLocationsForCharacter().get(character.getCharacterId()).get(locationId);

        req.setAttribute("image", currentLocation.getImage());
        req.setAttribute("sound", currentLocation.getSound());
        req.setAttribute("storyBlock", currentLocation.getStoryBlock());

        displayItemsInLocation(currentLocation.getItemsInLocation(), req);
        displayCharacterInventory(character.getInventory(), req);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void displayCharacterInventory(List<String> characterInventory, HttpServletRequest req) {
        if (characterInventory != null && !characterInventory.isEmpty()) {
            req.setAttribute("inventory", gameMaster.showItems(characterInventory));
        }
    }

    private void displayItemsInLocation(List<String> itemsInLocation, HttpServletRequest req) {
        if (!itemsInLocation.isEmpty()) {
            req.setAttribute("itemsInLocation", gameMaster.showItems(itemsInLocation));
            req.setAttribute("showItems", true);
        }
    }
}
