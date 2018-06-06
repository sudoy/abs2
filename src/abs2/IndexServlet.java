package abs2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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


		// localDateから現在時刻を抽出するか否かの判定
		LocalDate ld = null;

		// どの変数を見るか選択
		String check = null;

		if(req.getParameter("back") != null) {
			check = req.getParameter("back");
		} else if(req.getParameter("next") != null) {
			check = req.getParameter("next");
		} else if(req.getParameter("before") != null) {
			check = req.getParameter("before");
		} else if(req.getParameter("after") != null) {
			check = req.getParameter("after");
		}

		// checkに何もない場合現在日時抽出、ある場合はLocalDateに変換
		if(check != null) {
			ld = LocalDate.parse(check + "01日", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		} else {
			ld = LocalDate.now();
		};

		// 表示月とその月初末の変数宣言
		String today = null;
		LocalDate first = null;
		LocalDate last = null;

		// 前月と翌月のパラメータ取得

		if(req.getParameter("back") != null) {
			// 前月
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld.minusMonths(1));
			first = ld.withDayOfMonth(1).minusMonths(1);
			last = ld.withDayOfMonth(1).minusDays(1);
		} else if(req.getParameter("next") != null) {
			// 翌月
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld.plusMonths(1));
			first = ld.withDayOfMonth(1).plusMonths(1);
			last = ld.withDayOfMonth(1).plusMonths(2).minusDays(1);
		} else if(req.getParameter("before") != null) {
			// 前年
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld.minusYears(1));
			first = ld.withDayOfMonth(1).minusYears(1);
			last = ld.withDayOfMonth(1).plusMonths(1).minusDays(1).minusYears(1);
		} else if(req.getParameter("after") != null) {
			// 翌年
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld.plusYears(1));
			first = ld.withDayOfMonth(1).plusYears(1);
			last = ld.withDayOfMonth(1).plusMonths(1).minusDays(1).plusYears(1);
		} else {
			// 今月
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld);
			first = ld.withDayOfMonth(1);
			last = ld.withDayOfMonth(1).plusMonths(1).minusDays(1);
		};

		try {
			con = DBUtils.getConnection();
			// SQL
			sql = "SELECT m.id, m.dating, m.in_out, c.category_name, m.memo, m.money FROM myhab m LEFT JOIN categorylist c ON m.category = c.category_id WHERE m.dating BETWEEN ? AND ? ORDER BY m.dating";

			// SELECT命令の準備
			ps = con.prepareStatement(sql);

			// where句に代入
			ps.setString(1, first.toString());
			ps.setString(2, last.toString());

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
			session.setAttribute("today", today);
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
