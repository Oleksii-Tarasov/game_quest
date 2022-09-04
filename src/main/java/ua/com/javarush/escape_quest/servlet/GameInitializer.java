package ua.com.javarush.escape_quest.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.com.javarush.escape_quest.model.GameMaster.getGameMaster;

@WebServlet("/gameinitialization")
public class GameInitializer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");

        if (("<enter your name>").equals(nickname)) {
            nickname = "Unknown Hero";
        }

        getGameMaster().setPlayerNickname(nickname);
        getGameMaster().loadGameLocations();
        getGameMaster().loadGameItems();

        resp.sendRedirect(req.getContextPath() + "/location/?loc=prison");
    }
}
