package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/grabitem")
public class ItemServlet extends HttpServlet {
    private GameMaster gameMaster;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameMaster = (GameMaster) config.getServletContext().getAttribute("gameMaster");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] items = req.getParameterValues("item");

        Character character = (Character) req.getSession().getAttribute("character");

        String locationId = character.getCurrentLocationId();

        if (items == null) {
            resp.sendRedirect(req.getContextPath() + "/location/?id=" + locationId);
            return;
        }

        gameMaster.addItemsToCharacterInventory(character, items);
        gameMaster.removeItemsFromCharacterLocation(character, locationId, items);

        resp.sendRedirect(req.getContextPath() + "/location/?id=" + locationId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Character character = (Character) req.getSession().getAttribute("character");

        String locationId = character.getCurrentLocationId();

        if ("bossarena".equals(locationId)) {
            String item = req.getParameter("item");
            resp.sendRedirect(req.getContextPath() + "/bossfight/use?item=" + item);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/location/?id=" + locationId);
    }
}
