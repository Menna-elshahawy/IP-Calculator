package com.example;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String address = request.getParameter("address");
        String netmask = request.getParameter("netmask");
        String subsupernet = request.getParameter("subsupernet");
        
        String result = IPCalculator.calculateIPData(address, netmask, subsupernet);

        // Set attributes to be accessed in JSP
        request.setAttribute("address", address);
        request.setAttribute("netmask", netmask);
        request.setAttribute("subsupernet", subsupernet);
        request.setAttribute("result", result);

        // Forward request to JSP page for displaying result
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
