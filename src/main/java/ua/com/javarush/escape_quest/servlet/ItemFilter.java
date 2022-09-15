package ua.com.javarush.escape_quest.servlet;

import ua.com.javarush.escape_quest.model.Character;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/grabitem")
public class ItemFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Character character = (Character) request.getSession().getAttribute("character");
        String locationId = character.getCurrentLocationId();

        if (servletRequest.getParameterValues("item") == null) {
            response.sendRedirect(request.getContextPath() + "/location/?id=" + locationId);
        }
        else {
            filterChain.doFilter(request, response);
        }
    }
}
