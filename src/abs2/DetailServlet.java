package abs2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abs2.beans.Myhab;
import abs2.utils.DBUtils;

@WebServlet("/detail.html")
public class DetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp").forward(req, resp);
		req.setCharacterEncoding("utf-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "select * from myhab where id = ?";
			//select命令の準備
			ps = con.prepareStatement(sql);

			//select文にパラメータの内容をセット
			ps.setString(1, req.getParameter("id"));

			//select命令を実行
			rs = ps.executeQuery();
			
			rs.next();

			int id = rs.getInt("id");
			Date dating = rs.getDate("dating");
			int inout = rs.getInt("in_out");
			String category = rs.getString("category");
			String memo = rs.getString("memo");
			int money = rs.getInt("money");

			Myhab myhab = new Myhab(id, dating, inout, category, memo, money);
			req.setAttribute("myhab", myhab);

			//JSPへフォワード
			getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp")
					.forward(req, resp);

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (rs != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				DBUtils.close(con);
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp").forward(req, resp);
	}
}