package ua.com.javarush.escape_quest.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static ua.com.javarush.escape_quest.constant.GoodBadEnds.BAD_ENDS;
import static ua.com.javarush.escape_quest.constant.GoodBadEnds.GOOD_ENDS;
import static ua.com.javarush.escape_quest.model.GameMaster.getGameMaster;

@WebServlet("/location/")
public class LocationHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap gameLocations = getGameMaster().getGameLocations();
        String location = req.getParameter("loc");

        req.setAttribute("contentBlock", gameLocations.get(location));
        req.setAttribute("nickname", getGameMaster().getPlayerNickname());

        setEndGameStatusIfItsOver(req, location);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void setEndGameStatusIfItsOver(HttpServletRequest request, String location) {
        if (BAD_ENDS.contains(location)) {
            request.setAttribute("isGameOver", true);
        } else if (GOOD_ENDS.contains(location)) {
            request.setAttribute("isWinner", true);
        }
    }
}
