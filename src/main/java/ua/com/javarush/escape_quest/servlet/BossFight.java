package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bossfight/use")
public class BossFight extends HttpServlet {
    private GameMaster gameMaster;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameMaster = (GameMaster) config.getServletContext().getAttribute("gameMaster");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String item = req.getParameter("item");

        String effect = gameMaster.attackBossAndGetResult(item);
        req.getSession().setAttribute("effect", effect);

        if (gameMaster.getCharacter().isWinner()) {
            resp.sendRedirect(req.getContextPath() + "/location/?id=thronehall");
            return;
        }

        if (!gameMaster.canCharacterFight()) {
            resp.sendRedirect(req.getContextPath() + "/location/?id=hellend");
            return;
        }

        req.getSession().setAttribute("tries", gameMaster.getCharacter().getAmountOfLives());

        resp.sendRedirect(req.getContextPath() + "/location/?id=bossarena");
    }
}
