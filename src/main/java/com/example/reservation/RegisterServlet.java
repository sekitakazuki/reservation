package com.example.reservation;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		boolean success = UserDAO.registerUser(username, password);
		if (success) {
			resp.sendRedirect("login.jsp");
		} else {
			req.setAttribute("errorMessage", "そのユーザー名はすでに存在します");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
	}
}
