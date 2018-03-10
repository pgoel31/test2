package com.candidjava;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;

/**
 * Servlet implementation class LoginController
 */
public class listUpdateController extends HttpServlet {

	protected void doPost(HttpServletRequest request,
	   HttpServletResponse response) throws ServletException, IOException {
		 String[] checkedIds = request.getParameterValues("update");
		 System.out.println(checkedIds);
		
		PrintWriter out = response.getWriter();

		Connection c = null;
		PreparedStatement ps = null;
		try {
			if (c == null || c.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					throw new SQLException(e);
				}
				// loads driver
				c = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/world", "root", "1234");
			}

			HttpSession session = request.getSession(false);
			
//			if(checkedIds==null)
//			{
//				request.getRequestDispatcher("updateError.html").forward(request,	response);
//				return;
//			}
			int numberOfItems = checkedIds.length;
			int j = 0;
			for (int i = 0; i < numberOfItems; i++) 
			{  
				//ResultSet rs = null;
				String val = checkedIds[i];
						System.out.println("the value of donation id ==>" + val);
						ps = c.prepareStatement(" update Donation_dtls DD set dd.still_available = 'N' WHERE dd.donation_id = ?");
						ps.setString(1, val);
						j = ps.executeUpdate();
			}
			if (j != 0) {
				out.println("<br>Record updated successfully");

			} else {
				out.println("Failed to update, please try again with correct credentials! Or please "
						+ "contact your system admin ! ");
			}
			return;
						
		}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				String m = e.getMessage();
				
			} finally {

				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) { 
					}
				}
				if (c != null) {
					try {
						c.close();
					} catch (SQLException e) {  
					}
				}
			}
		}

	}
