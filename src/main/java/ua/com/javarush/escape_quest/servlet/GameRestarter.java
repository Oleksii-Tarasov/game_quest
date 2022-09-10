package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/restart")
public class GameRestarter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameMaster gameMaster = GameMaster.getGameMaster();

//        Map playerInventory = gameMaster.getPlayerInventory();
//
//        if (playerInventory != null && !playerInventory.isEmpty()) {
//            playerInventory.clear();
//        }

        resp.sendRedirect(req.getContextPath() + "/location/?title=prison");
    }
}
