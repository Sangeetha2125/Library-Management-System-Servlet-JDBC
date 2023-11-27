

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLibrary
 */
@WebServlet("/ServletLibrary")
public class ServletLibrary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLibrary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/library_jdbc","root","");
			PreparedStatement ps = con.prepareStatement("insert into book(title,author,publisher,edition,price) values(?,?,?,?,?)");
			Enumeration<?> en = request.getParameterNames();
			int i = 1;
			while(en.hasMoreElements()) {
				Object obj = en.nextElement();
				String parameter = (String) obj;
				String value = request.getParameter(parameter);
				if(i==5) {
					int price = Integer.parseInt(value);
					ps.setInt(i, price);
				}
				else {
					ps.setString(i,value);
				}
				i+=1;
			}
			int res = ps.executeUpdate();
			if(res>0) {
				out.println("<script>alert('Data Inserted Successfully');window.location.href='index.html';</script>");
			}
		}
		catch(Exception ex) {
			out.println(ex);
		}

	}

}
