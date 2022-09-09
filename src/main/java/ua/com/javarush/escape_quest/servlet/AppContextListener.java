package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.GameMaster;
import ua.com.javarush.escape_quest.service.GameConstructor;
import ua.com.javarush.escape_quest.service.ResourceLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        GameConstructor gameConstructor = new GameConstructor(new ResourceLoader());
        gameConstructor.createLocations();
        gameConstructor.createItems();

        servletContext.setAttribute("gameConstructor", gameConstructor);
        servletContext.setAttribute("gameLocations", gameConstructor.getGameLocations());
        servletContext.setAttribute("gameItems", gameConstructor.getGameItems());
    }
}
