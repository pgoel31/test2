package com.candidjava;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	String un = null;
	String pwd = null;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		PreparedStatement ps = null;
		un = request.getParameter("contactnumber");
		pwd = request.getParameter("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/world", "root", "1234");

			ps = c.prepareStatement(" SELECT USER_ID, Name, Contact_Number FROM login_details "
					+ "where Contact_Number = ? and Password = ?");

			if ((un != null && un != "") && (pwd != null && pwd != "")) {
				ps.setString(1, un);
				ps.setString(2, pwd);
			} else {
				ps.setObject(1, null);
				ps.setObject(2, null);
			}

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String USER_ID = rs.getString(1);
				HttpSession session = request.getSession();
				if (USER_ID != null && USER_ID != "") {
					session.setAttribute("USER_ID", USER_ID);
					session.setAttribute("un", un);
				}

				ps = c.prepareStatement("select NAME ,Contact_Number ,Location ,Address ,"
						+ "CASE WHEN dd.Item_Food = 'Y' THEN 'Available' else '' END Food ,"
						+ "CASE WHEN dd.Item_Clothing = 'Y' THEN 'Available' else '' END Clothes ,"
						+ "CASE WHEN dd.Item_HouseHold = 'Y' THEN 'Available' else '' END Household, "
						+ "Other_Item_dtl Other "
						+ "from Donation_dtls DD where dd.User_id <> ? AND still_available = 'Y';");

				ps.setString(1, USER_ID);

				ResultSet rs1 = ps.executeQuery();

				List dataList = new ArrayList();

				while (rs1.next()) {

					// Add records into data list

					dataList.add(rs1.getString("NAME"));
					dataList.add(rs1.getString("Contact_Number"));
					dataList.add(rs1.getString("Location"));
					dataList.add(rs1.getString("Address"));
					dataList.add(rs1.getString("Food"));
					dataList.add(rs1.getString("Clothes"));
					dataList.add(rs1.getString("Household"));
					dataList.add(rs1.getString("Other"));

				}
				request.setAttribute("data", dataList);

				ps = c.prepareStatement("select Donation_id, NAME ,Contact_Number ,Location ,Address ,"
						+ "CASE WHEN dd.Item_Food = 'Y' THEN 'Available' else '' END Food ,"
						+ "CASE WHEN dd.Item_Clothing = 'Y' THEN 'Available' else '' END Clothes ,"
						+ "CASE WHEN dd.Item_HouseHold = 'Y' THEN 'Available' else '' END Household, "
						+ "Other_Item_dtl Other , "
						+ "CASE WHEN dd.Still_Available = 'Y' THEN 'Yes' else 'No' END  'Still Available' "
						+ "from Donation_dtls DD where dd.User_id = ? AND still_available = 'Y';");

				ps.setString(1, USER_ID);

				rs1 = ps.executeQuery();
				List dataList1 = new ArrayList();
				while (rs1.next()) {

					// Add records into data list

					dataList1.add(rs1.getString("NAME"));
					dataList1.add(rs1.getString("Contact_Number"));
					dataList1.add(rs1.getString("Location"));
					dataList1.add(rs1.getString("Address"));
					dataList1.add(rs1.getString("Food"));
					dataList1.add(rs1.getString("Clothes"));
					dataList1.add(rs1.getString("Household"));
					dataList1.add(rs1.getString("Other"));
					dataList1.add(rs1.getString("Still Available"));
					dataList1.add(rs1.getString("Donation_id"));

				}
				request.setAttribute("data1", dataList1);

				request.getRequestDispatcher("profile.jsp").forward(request,
						response);
			}
			
				
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("error.html");
			return;
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* ignored */
					String n = e.getMessage();
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) { /* ignored */
					String l = e.getMessage();
				}
			}
		}
	}

}
