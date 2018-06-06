package abs2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abs2.beans.Myhab;
import abs2.utils.DBUtils;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		Date now = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		String today = f1.format(now);

		SimpleDateFormat f2 = new SimpleDateFormat("yyyy年MM月");
		String today2 = f2.format(now);

		System.out.println(today);

		try {
			con = DBUtils.getConnection();
			// SQL

//			String today = req.getParameter("today");
//			System.out.println(todays);
//			System.out.println(today);

			sql = "SELECT m.id, m.dating, m.in_out, c.category_name, m.memo, m.money FROM myhab m LEFT JOIN categorylist c ON m.category = c.category_id WHERE m.dating > ? ORDER BY m.dating";


			// SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, today);
			// 実行
			rs = ps.executeQuery();

			List<Myhab> list = new ArrayList<>();
			while(rs.next()) {
				Myhab a = new Myhab(
						rs.getInt("id"),
						rs.getDate("dating"),
						rs.getInt("in_out"),
						rs.getString("category_name"),
						rs.getString("memo"),
						rs.getInt("money"));

				list.add(a);
			}
			// JavaBeansをJSPに渡す
			session.setAttribute("today", today2);
			session.setAttribute("list", list);

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
