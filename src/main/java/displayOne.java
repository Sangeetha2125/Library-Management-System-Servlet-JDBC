

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class displayOne
 */
@WebServlet("/displayOne")
public class displayOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/library_jdbc","root","");
			out.println("<head><title>Search Book</title><style>.book{font-size:1.1em;padding:0.8em 1em;border-radius:12px;margin:1em;color:black;border:2px solid black;}.book-container{display:grid;grid-template-columns:1fr 1fr;}.nav-container{margin:2em;}nav{display: flex;flex-direction:row;justify-content: end;width: 100%;gap:2em;}nav a{text-decoration: none;font-size: 1.1em;border: 2px solid black;border-radius: 6px;padding: 8px 12px;color: black;}</style></head>");
			out.println("<div class='nav-container'><nav><a href='index.html'>Insert Book</a><a href='displayAll'>Display All Books</a><a href='displayOne.html'>Search Book</a></nav></div>");
			out.println("<div class='book-container'>");
			String search = request.getParameter("Title");
			PreparedStatement ps = con.prepareStatement("select * from book where title like ? or author like ? or publisher like ? or edition like ?");
			ps.setString(1,'%'+ search.substring(0,1).toUpperCase()+search.substring(1) +'%');
			ps.setString(2,'%'+ search.substring(0,1).toUpperCase()+search.substring(1) +'%');
			ps.setString(3,'%'+ search.substring(0,1).toUpperCase()+search.substring(1) +'%');
			ps.setString(4,'%'+ search.substring(0,1).toUpperCase()+search.substring(1) +'%');
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				out.println("<div class='book'>");
				out.println("<p>Title: "+rs.getString("title")+"</p>");
				out.println("<p>Author: "+rs.getString("author")+"</p>");
				out.println("<p>Publisher: "+rs.getString("publisher")+"</p>");
				out.println("<p>Edition: "+rs.getString("edition")+"</p>");
				out.println("<p>Price: "+rs.getInt("price")+"</p>");
				out.println("</div>");
			}
			out.println("</div>");
		}
		catch(Exception ex) {
			out.println(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
