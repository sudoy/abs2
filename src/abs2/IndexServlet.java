package abs2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abs2.beans.Myhab;
import abs2.utils.DBUtils;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();
			// SQL
//			sql = "SELECT m.id, m.dating, m.in_out, c.category_name AS category, m.memo, m.money FROM myhab m"
//					+ "LEFT JOIN categorylist c ON m.category = c.category_id ORDER BY m.id";

			sql = "SELECT id, dating,in_out, category, memo, money FROM myhab ORDER BY id";

//			sql = "SELECT category_id, category_name FROM categorylist ORDER BY category_id";

			// SELECT命令の準備
			ps = con.prepareStatement(sql);
			// 実行
			rs = ps.executeQuery();

			List<Myhab> list = new ArrayList<>();
			while(rs.next()) {
				Myhab a = new Myhab(
						rs.getInt("id"),
						rs.getDate("dating"),
						rs.getInt("in_out"),
						rs.getString("category"),
						rs.getString("memo"),
						rs.getInt("money"));

				list.add(a);
				System.out.println(a);
			}
			// JavaBeansをJSPに渡す
			req.setAttribute("list", list);

			getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
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

		resp.sendRedirect("index.html");
		}
}
