package com.candidjava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.CallableStatement;

/**
 * Servlet implementation class LoginController
 */
public class RegisterController extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String un = request.getParameter("contactnumber");
		String pw = request.getParameter("password");
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
						"jdbc:mysql://localhost:3306/world", "root", "1234"); // gets
			}
            
			ps = c.prepareStatement(" INSERT INTO login_details (Name, Contact_number, Password) VALUES (?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, un);
			ps.setString(3, pw);

			int i = ps.executeUpdate();

			if (i != 0) {
				out.println("<br>You have successfully Registered. Please Login to continue!");

			} else {
				out.println("Failed to register, please try again with correct credentials! Or please "
						+ "contact your system admin ! ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String m = e.getMessage();
			if(m.contains("Duplicate"))
			out.println("You have already registered with the given details. Please login!");
		}
		finally
		{
			
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (c != null) {
		        try {
		            c.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		}
	}

}
