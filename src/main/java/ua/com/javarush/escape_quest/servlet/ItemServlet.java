package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/grabitem")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Character character = (Character) req.getSession().getAttribute("character");
        String locationId = character.getCurrentLocationId();
        String itemId = req.getParameter("item");

        GameMaster gameMaster = (GameMaster) req.getSession().getServletContext().getAttribute("gameMaster");
        gameMaster.moveItemFromLocationToCharacterInventory(character, locationId, itemId);

        resp.sendRedirect(req.getContextPath() + "/location/?id=" + locationId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Character character = (Character) req.getSession().getAttribute("character");
        String locationId = character.getCurrentLocationId();

        if ("bossarena".equals(locationId)) {
            String itemId = req.getParameter("item");
            resp.sendRedirect(req.getContextPath() + "/bossfight/use?item=" + itemId);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/location/?id=" + locationId);
    }
}
