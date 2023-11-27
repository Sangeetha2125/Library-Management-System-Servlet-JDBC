

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
 * Servlet implementation class displayAll
 */
@WebServlet("/displayAll")
public class displayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayAll() {
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
			out.println("<head><title>All Books</title><style>.delete-book{display:block;padding:10px;text-decoration:none;border-radius:8px;text-align:center;font-size:1.1em;background-color:black;color:white;}.book{font-size:1.1em;padding:0.8em 1em;border-radius:12px;margin:1em;color:black;border:2px solid black;}.book-container{display:grid;grid-template-columns:1fr 1fr;}.nav-container{margin:2em;}nav{display: flex;flex-direction:row;justify-content: end;width: 100%;gap:2em;}nav a{text-decoration: none;font-size: 1.1em;border: 2px solid black;border-radius: 6px;padding: 8px 12px;color: black;}</style></head>");
			out.println("<div class='nav-container'><nav><a href='index.html'>Insert Book</a><a href='displayAll'>Display All Books</a><a href='displayOne.html'>Search Book</a></nav></div>");
			out.println("<div class='book-container'>");
			PreparedStatement ps = con.prepareStatement("select * from book");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				out.println("<div class='book'>");
				out.println("<p>Title: "+rs.getString("title")+"</p>");
				out.println("<p>Author: "+rs.getString("author")+"</p>");
				out.println("<p>Publisher: "+rs.getString("publisher")+"</p>");
				out.println("<p>Edition: "+rs.getString("edition")+"</p>");
				out.println("<p>Price: "+rs.getInt("price")+"</p>");
				out.println("<a href='deleteOne?id="+rs.getInt("accno")+"' class='delete-book'>Delete Book</a>");
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
