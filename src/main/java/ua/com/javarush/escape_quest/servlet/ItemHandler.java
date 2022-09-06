package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ItemHandler", value = "/grabitem")
public class ItemHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] items = req.getParameterValues("item");

        if (items == null || items.length >=3) {
            resp.sendRedirect(req.getContextPath() + "/location/?loc=firehall");
            return;
        }

        addItemToInventory(items);

        resp.sendRedirect(req.getContextPath() + "/location/?loc=firehall");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();

        String location = (String) currentSession.getAttribute("location");

        if ("bossarena".equals(location)) {
            String item = request.getParameter("item");
            response.sendRedirect(request.getContextPath() + "/bossfight/useOnBoss?item=" + item);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/location/?id=" + location);
    }

    private void addItemToInventory(String[] items) {
        GameMaster gameMaster = GameMaster.getGameMaster();

        Map<String, String> gameItems = gameMaster.getGameItems();
        Map<String, String> playerInventory = gameMaster.getPlayerInventory();

        if (playerInventory == null) {
            playerInventory = new HashMap<>();
        }

        if (playerInventory.size() == 3) {
            return;
        }

        for (String item : items) {
            if (gameItems.containsKey(item)) {
                String itemDescription = gameItems.get(item);
                playerInventory.put(item, itemDescription);
                gameItems.remove(item);
            }
        }

        gameMaster.setPlayerInventory(playerInventory);
    }
}
