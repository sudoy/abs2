package abs2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");

		String  dating = req.getParameter("dating");
		String inout = req.getParameter("inout");
		String category = req.getParameter("category");
		String memo = req.getParameter("memo");
		String money = req.getParameter("money");


		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("abs2");
			con = ds.getConnection();

			sql = "insert into todo(dating, in_out, category, memo, money) value(?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, dating);
			ps.setString(2, inout);
			ps.setString(3, category);
			ps.setString(4, memo);
			ps.setString(5, money);

			ps.executeUpdate();

			resp.sendRedirect("index.html");

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
			}
		}
	}
}