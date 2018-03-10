package com.candidjava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.CallableStatement;

/**
 * Servlet implementation class LoginController
 */
public class DonateController extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String contactNumber = null;
		String address = request.getParameter("Address");
		String location = request.getParameter("Location");
		String[] checkedIds = request.getParameterValues("cb1");
		String foodPresent = "N";
		String clothesPresent = "N"; 
		String hhPresent = "N"; 
		
		int numberOfItems = checkedIds.length;
		for (int i = 0; i < numberOfItems; i++) 
		{
			String val = checkedIds[i];
			if(val.equals("food"))
					{
				foodPresent="Y";
					}
			if(val.equals("clothes"))
					{
				clothesPresent="Y";
					}
			if(val.equals("householditems"))
					{
				hhPresent="Y";
					}
			
		}
		String others = request.getParameter("others");

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

			HttpSession session = request.getSession(false);

			//			if (request.getParameter("Contnumber") != null
			//					&& request.getParameter("Contnumber").length() > 0) {
			contactNumber = request.getParameter("Contnumber");
			//			} else {
			//				contactNumber = (String) session.getAttribute("un");
			//			}
			String tempContactNumber = (String) session.getAttribute("un");
			ps = c.prepareStatement("SELECT User_id FROM "
					+ "login_details where Contact_Number = ifnull( ?, Contact_Number)");
			ps.setObject(1, tempContactNumber);

			ResultSet rs = null;
			rs = ps.executeQuery();
			String user_id = null;
			while (rs.next()) {
				user_id = rs.getString(1);
				System.out.println("the value of user_id ==>" + user_id);
				ps = c.prepareStatement(" INSERT INTO Donation_dtls (User_id, Name , Contact_Number, "
						+ "Location , Address, Item_Food , Item_Clothing , Item_HouseHold, Still_Available, "
						+ "Other_Item_dtl ) VALUES (?, ?, ?, ?, ?, ?, ?, ?,'Y', ?)");
				ps.setString(1, user_id);
				ps.setString(2, name);
				ps.setString(3, contactNumber);
				ps.setString(4, location);
				ps.setString(5, address);
				ps.setString(6, foodPresent);
				ps.setString(7, clothesPresent);
				ps.setString(8, hhPresent);
				ps.setString(9, others);

				int i = ps.executeUpdate();

				if (i != 0) {
					out.println("<br>Donation items are successfully recorded, Receivers will contact you shortly");

				} else {
					out.println("Failed to register, please try again with correct credentials! Or please "
							+ "contact your system admin ! ");
				}
				return;
			}
			
		}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				String m = e.getMessage();
				
			} finally {

				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) { /* ignored */
					}
				}
				if (c != null) {
					try {
						c.close();
					} catch (SQLException e) { /* ignored */
					}
				}
			}
		}

	}
