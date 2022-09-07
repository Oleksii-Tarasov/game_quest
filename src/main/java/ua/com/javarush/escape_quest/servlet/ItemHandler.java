package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ItemHandler", value = "/grabitem")
public class ItemHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GameMaster gameMaster = GameMaster.getGameMaster();

        String location = gameMaster.getCurrentLocation();
        String[] items = req.getParameterValues("item");

        if (items == null) {
            resp.sendRedirect(req.getContextPath() + "/location/?loc=" + location);
            return;
        }

        addItemToInventory(gameMaster, items);

        resp.sendRedirect(req.getContextPath() + "/location/?loc=" + location);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GameMaster gameMaster = GameMaster.getGameMaster();

        String location = gameMaster.getCurrentLocation();

        if ("bossarena".equals(location)) {
            String item = request.getParameter("item");
            response.sendRedirect(request.getContextPath() + "/bossfight/use?item=" + item);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/location/?loc=" + location);
    }

    private void addItemToInventory(GameMaster gameMaster, String[] items) {
        Map<String, String> gameItems = gameMaster.getGameItems();

        for (String item : items) {
            if (gameItems.containsKey(item)) {
                String itemDescription = gameItems.get(item);
                gameMaster.addItemToPlayerInventory(item, itemDescription);
                gameItems.remove(item);
            }
        }
    }
}
