package abs2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@WebServlet("/copy.html")
public class CopyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
			getServletContext().getRequestDispatcher("/WEB-INF/copy.jsp")
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		String dating = req.getParameter("dating");
		String inout = req.getParameter("in_out");
		String category = req.getParameter("category");
		String memo = req.getParameter("memo");
		String money = req.getParameter("money");



		//バリデーションチェック
		List<String> errors = validate(dating, inout, category, memo, money);
		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = DBUtils.getConnection();

			sql = "insert into myhab(dating, in_out, category, memo, money) value(?, ?, ?, ?, ?);";
			ps = con.prepareStatement(sql);

			ps.setString(1, dating);
			ps.setString(2, inout);
			ps.setString(3, category);
			ps.setString(4, memo);
			ps.setString(5, money);


			ps.executeUpdate();
			List<String> successes = new ArrayList<>();
			if(category.equals("1")) {
				category="食費";
			}else if (category.equals("2")) {
				category = "日用品";
			}else if(category.equals("3")) {
				category = "交際費";
			}else {
				return;
			}
			successes.add("「" + dating + " " + category + " " + money + "」" + "を登録しました。");
			session.setAttribute("successes", successes);

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

	private List<String> validate(String dating, String inout, String category, String memo, String money) {
		List<String> errors = new ArrayList<>();

		//日付の必須入力
		if (dating.equals("")) {
			errors.add("日付は必須入力です。");
		}

		//カテゴリーの必須入力
		if (!category.equals("1") && !category.equals("2") && !category.equals("3") && !category.equals("4")&& ! category.equals("5")) {
			errors.add("カテゴリーは必須入力です。");
		}

		//金額の必須入力
		if (money.equals("")) {
			errors.add("金額は必須入力です。");
		}

		return errors;
	}
}