package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/restart")
public class GameRestart extends HttpServlet {
    private GameMaster gameMaster;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameMaster = (GameMaster) config.getServletContext().getAttribute("gameMaster");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        gameMaster.createGameWorld();
        gameMaster.getCharacter().setAmountOfLives(3);
        gameMaster.getCharacter().getInventory().clear();
        gameMaster.getCharacter().setWinner(false);
        req.getSession().setAttribute("effect", "");

        resp.sendRedirect(req.getContextPath() + "/location/?title=prison");
    }
}
