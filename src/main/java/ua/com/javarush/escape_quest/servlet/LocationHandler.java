package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;
import ua.com.javarush.escape_quest.model.Location;
import ua.com.javarush.escape_quest.model.Player;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static ua.com.javarush.escape_quest.constant.GoodBadEnds.BAD_ENDS;
import static ua.com.javarush.escape_quest.constant.GoodBadEnds.GOOD_ENDS;

@WebServlet("/location/")
public class LocationHandler extends HttpServlet {
    private Map<String, Location> gameLocations;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        gameLocations = (Map<String, Location>) servletContext.getAttribute("gameLocations");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameMaster gameMaster = GameMaster.getGameMaster();

        String token = req.getParameter("loc");
//        GameConstructor gameConstructor = new GameConstructor(new ResourceLoader());
//        gameConstructor.createLocations();

        Location location = gameLocations.get(token);
        HttpSession session = req.getSession();
        Player player = (Player) session.getAttribute("player");

        req.setAttribute("contentBlock", location.getPagePath());
        req.setAttribute("nickname", player.getNickname());
        req.setAttribute("inventory", player.getInventory());


//        req.setAttribute("contentBlock", gameLocations.get(location));
//        req.setAttribute("nickname", gameMaster.getPlayerNickname());
//        req.setAttribute("inventory", gameMaster.getPlayerInventory());

        displayEndGameStatusIfItsOver(req, location.getTitle());
        displayGameItemsIfItsPossible(gameMaster, req, location.getTitle());
        displayGameTries(gameMaster, req, location.getTitle());

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
