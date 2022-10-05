package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/restart")
public class GameRestart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Character character = (Character) req.getSession().getAttribute("character");
        GameMaster gameMaster = (GameMaster) req.getServletContext().getAttribute("gameMaster");

        gameMaster.resetCharacterStats(character);

        req.getSession().setAttribute("effect", "");

        resp.sendRedirect(req.getContextPath() + "/location/?id=prison");
    }
}
