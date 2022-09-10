package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ItemServlet", value = "/grabitem")
public class ItemServlet extends HttpServlet {
    private GameMaster gameMaster;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameMaster = (GameMaster) config.getServletContext().getAttribute("gameMaster");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] items = req.getParameterValues("item");

        String locationTitle = gameMaster.getCharacter().getCurrentLocation();

        if (items == null) {
            resp.sendRedirect(req.getContextPath() + "/location/?title=" + locationTitle);
            return;
        }

        gameMaster.addItemsToCharacterInventory(items);
        gameMaster.removeItemsFromLocation(locationTitle, items);

        resp.sendRedirect(req.getContextPath() + "/location/?title=" + locationTitle);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String locationTitle = gameMaster.getCharacter().getCurrentLocation();

        if ("bossarena".equals(locationTitle)) {
            String item = request.getParameter("item");
            response.sendRedirect(request.getContextPath() + "/bossfight/use?item=" + item);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/location/?title=" + locationTitle);
    }
}
