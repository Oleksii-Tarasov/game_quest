package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.service.GameMaster;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "")
public class GameStart extends HttpServlet {
    private GameMaster gameMaster;
    private long characterId;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameMaster = (GameMaster) config.getServletContext().getAttribute("gameMaster");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("image", "/img/introduce.jpg");
        req.setAttribute("storyBlock", "/view/introduce.jsp");
        characterId = 0;

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nickname = req.getParameter("nickname");
        characterId++;

//        gameMaster.loadGameLocations();
//        gameMaster.loadGameItems();
        gameMaster.loadCharacter(characterId, nickname);

        gameMaster.setLocationsForCurrentCharacter(characterId);

        HttpSession session = req.getSession();
        session.setAttribute("character", gameMaster.getGameCharacters().get(characterId));

        resp.sendRedirect(req.getContextPath()  + "/location/?id=prison");
    }
}
