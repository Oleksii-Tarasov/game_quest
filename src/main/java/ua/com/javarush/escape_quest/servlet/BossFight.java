package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bossfight/use")
public class BossFight extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Character character = (Character) req.getSession().getAttribute("character");
        GameMaster gameMaster = (GameMaster) req.getSession().getServletContext().getAttribute("gameMaster");

        String item = req.getParameter("item");
        String effect = gameMaster.attackBossAndGetResult(character, item);

        req.getSession().setAttribute("effect", effect);

        if (character.isWinner()) {
            resp.sendRedirect(req.getContextPath() + "/location/?id=thronehall");
            return;
        }

        if (!gameMaster.canCharacterFight(character)) {
            resp.sendRedirect(req.getContextPath() + "/location/?id=hellend");
            return;
        }

        req.getSession().setAttribute("tries", character.getAmountOfLives());

        resp.sendRedirect(req.getContextPath() + "/location/?id=bossarena");
    }
}
