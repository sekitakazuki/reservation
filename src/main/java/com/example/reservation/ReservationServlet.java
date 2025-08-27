package com.example.reservation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@MultipartConfig
public class ReservationServlet extends HttpServlet {
	private final ReservationDAO reservationDAO = new ReservationDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("list".equals(action) || action == null) {
			String seatchTerm = req.getParameter("search");
			String sortBy = req.getParameter("sortBy");
			String sortOder = req.getParameter("sortOder");
			int page = 1;
			int recordsPerPage = 5;
			
			if(req.getParameter("page") !=null) {
				page = Integer.parseInt(req.getParameter("page"));
			}
			List<Reservation> allReservtionsList = reservationDAO.searchAndSortReservations(seatchTerm, sortByString, sortOder);
			
			int start = (page - 1) * recordsPerPage;
			int end = Math.min(start + recordsPerPage,allReservations.size());
			List<Reservation> reservations = allReservations.subList(start,end);
			
			int noOfPages = (int) Math.ceil(allReservations.size() * 1.0 / recordsPerPage);
			
			req.setAttribute("reservations",reservations);
			req.setAttribute("noOfPages",noOfPages);
			req.setAttribute("currentPage", page);
			req.setAttribute("searchTerm",seatchTerm);
			req.setAttribute("sortBy",sortBy);
			req.setAttribute("sertOder",sortOder);
			
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/list.jsp");
			rd.forward(req, resp);
			} else if("edit".equals(action) ) {
				int id = Integer.parseInt(req.getParameter("id"));
				Reservation reservation = reservationDAO.getReservationById(id);
				req.setAttribute("reservation", reservation);
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/edit.jsp");
				rd.forward(req,resp);
			} else if ("export_csv".equals(action)) {
				exportCsv(req,resp);
			} else if ("clean_up".equals(action)) {
				reservationDAO.celanUpPastReservations();
				req.setAttribute("successMessage", "過去の予約をクリーンアップしました。");
				resp.sendRedirect("reservation?action=list");
			} else {
				resp.sendRedirect("index.jsp");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("add".equals(action)) {
			String name = req.getParameter("name");
			String reservationTimeString = req.getParameter("reservation_time");
			
			if (name == null || name.trim().isEmpty()) {
				req.setAttribute("errorMessage", "名前は必須です。");
				RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
				rd.forward(req, resp);
				return;
			}
			if (reservationTimeString == null || reservationTimeString.isEmpty());
				req.setAttribute("errorMessage", "希望日数は必須です");
				RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
				rd.forward(req,resp);
				return;
		}
		
		try {
			LocalDateTime reservationTime = LocalDateTime.parse(reservationTimeString);
			if (reservationTime.isBefore(LocalDateTime.now())) {
				
			}
		}
	}
}
