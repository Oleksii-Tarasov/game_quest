package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static ua.com.javarush.escape_quest.constant.GoodBadEnds.BAD_ENDS;
import static ua.com.javarush.escape_quest.constant.GoodBadEnds.GOOD_ENDS;

@WebServlet("/location/")
public class LocationHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameMaster gameMaster = GameMaster.getGameMaster();
        HashMap gameLocations = gameMaster.getGameLocations();

        String location = req.getParameter("loc");
        gameMaster.setCurrentLocation(location);

        req.setAttribute("contentBlock", gameLocations.get(location));
        req.setAttribute("nickname", gameMaster.getPlayerNickname());
        req.setAttribute("inventory", gameMaster.getPlayerInventory());

        displayEndGameStatusIfItsOver(req, location);
        displayGameItemsIfItsPossible(gameMaster, req, location);
        displayGameTries(gameMaster, req, location);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void displayEndGameStatusIfItsOver(HttpServletRequest request, String location) {
        if (BAD_ENDS.contains(location)) {
            request.setAttribute("isGameOver", true);
        } else if (GOOD_ENDS.contains(location)) {
            request.setAttribute("isWinner", true);
        }
    }

    private void displayGameItemsIfItsPossible(GameMaster gameMaster, HttpServletRequest request, String location){
        if ("firehall".equals(location)) {
            request.setAttribute("gameItems", gameMaster.getGameItems());
        }
    }

    private void displayGameTries(GameMaster gameMaster, HttpServletRequest request, String location) {
        if ("bossarena".equals(location)) {
            request.setAttribute("tries", gameMaster.getTries());
        }
    }
}
