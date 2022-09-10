package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.com.javarush.escape_quest.constant.BossFightMessage.*;

@WebServlet("/bossfight/use")
public class BossFight extends HttpServlet {
    private GameMaster gameMaster;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameMaster = (GameMaster) config.getServletContext().getAttribute("gameMaster");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String item = req.getParameter("item");

        if ("waterBucket".equals(item)) {
            resp.sendRedirect(req.getContextPath() + "/location/?title=thronehall");
            return;
        }

        String effect = gameMaster.attackBossAndGetResult(item);
        req.getSession().setAttribute("effect", effect);

        gameMaster.isCharacterLoser();

        int tries = gameMaster.getCharacter().getAmountOfLives();
        tries--;
        gameMaster.getCharacter().setAmountOfLives(tries);

        if (tries == 0 || gameMaster.getCharacter().getInventory().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/location/?title=hellend");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/location/?title=bossarena");
    }
    private String getReactionAfterAttackOnBoss(String item) {
        return switch (item) {
            case "threads" -> REACTION_TO_THREADS;
            case "book" -> REACTION_TO_BOOK;
            case "sword" -> REACTION_TO_SWORD;
            case "pizza" -> REACTION_TO_PIZZA;
            case "textbook" -> REACTION_TO_TEXTBOOK;
            default -> "";
        };
    }

}
