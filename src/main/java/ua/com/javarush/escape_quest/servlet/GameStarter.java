package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class GameStarter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("contentBlock", "/view/introduce.jsp");

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");

        if (("<enter your name>").equals(nickname)) {
            nickname = "Unknown Hero";
        }

        GameMaster gameMaster = GameMaster.getGameMaster();

        gameMaster.setPlayerNickname(nickname);
        gameMaster.loadGameLocations();
        gameMaster.loadGameItems();
        gameMaster.setTries(3);

        resp.sendRedirect(req.getContextPath() + "/location/?loc=prison");
    }
}
