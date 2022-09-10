package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("")
public class GameStarter extends HttpServlet {
    private GameMaster gameMaster;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        gameMaster = (GameMaster) servletContext.getAttribute("gameMaster");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("image", "/img/introduce.jpg");
        req.setAttribute("storyBlock", "/view/introduce.jsp");

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nickname = req.getParameter("nickname");

        if (("<enter your name>").equals(nickname)) {
            nickname = "Unknown Hero";
        }

        gameMaster.createCharacter(nickname);
        gameMaster.createGameWorld();

        resp.sendRedirect(req.getContextPath() + "/location/?title=prison");
    }
}
