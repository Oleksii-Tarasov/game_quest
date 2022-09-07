package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.com.javarush.escape_quest.constant.BossFightMessage.*;

@WebServlet("/bossfight/use")
public class BossFight extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String item = req.getParameter("item");

        if ("waterBucket".equals(item)) {
            resp.sendRedirect(req.getContextPath() + "/location/?loc=thronehall");
            return;
        }

        String reaction = getReactionAfterAttackOnBoss(item);
        req.getSession().setAttribute("message", reaction);

        GameMaster gameMaster = GameMaster.getGameMaster();
        gameMaster.getPlayerInventory().remove(item);

        int tries = gameMaster.getTries();
        tries--;
        gameMaster.setTries(tries);

        if (tries == 0 || gameMaster.getPlayerInventory().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/location/?loc=hellend");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/location/?loc=bossarena");
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
