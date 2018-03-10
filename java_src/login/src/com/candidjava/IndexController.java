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
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.CallableStatement;

/**
 * Servlet implementation class LoginController
 */
public class IndexController extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String name = null;
		String un = null;
		String pw = null;
		
		PrintWriter out = response.getWriter();

		Connection c = null;
		PreparedStatement ps = null;
		try {
			
			HttpSession session=request.getSession(false);
			
			
			 name = request.getParameter("name");			 
			 pw = request.getParameter("Address");
			
			if(request.getParameter("Contnumber")!=null && request.getParameter("Contnumber").length()>0){
				un = request.getParameter("Contnumber"); 
			}else{
				un=(String)session.getAttribute("un");  
			}
			
			if(request.getParameter("name")!=null && request.getParameter("name").length()>0){
				name = request.getParameter("name"); 
			}else{
				name=(String)session.getAttribute("name");  
			}
			
			
			if (c == null || c.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					throw new SQLException(e);
				}
				// loads driver
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "1234"); // gets
			}
			
			ps = c.prepareStatement("SELECT User_id,  Name, Contact_Number FROM login_details where Contact_Number = ifnull( ?, Contact_Number)");
            
		//	ps = c.prepareStatement(" INSERT INTO doonate (Name, Contact_number, Email_Id) VALUES (?, ?, ?)");
			ps.setObject(1, un);
			
			ResultSet rs=null;
			rs = ps.executeQuery();
//			
//		while(rs.next()){
////			String user_id = rs.getString(1);
////			System.out.println("the value of user_id ==>"+user_id);
////			System.out.println("un:"+un);
////			
////			String name1 = rs.getString(2);
////			System.out.println("the value of name ==>"+name1);
////			System.out.println("un:"+un);
////			response.sendRedirect("index.html");
//			return;
//		}
//		response.sendRedirect("error.html");
//		return;
//		
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String m = e.getMessage();
			
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
