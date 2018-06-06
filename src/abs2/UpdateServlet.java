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

@WebServlet("/update.html")
public class UpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
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
			int inOut = rs.getInt("in_out");
			String category = rs.getString("category");
			String memo = rs.getString("memo");
			int money = rs.getInt("money");

			Myhab myhab = new Myhab(id, dating, inOut, category, memo, money);
			req.setAttribute("myhab", myhab);

			//JSPへフォワード
			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp")
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

		String id = req.getParameter("id");
		String dating = req.getParameter("dating");
		String inOut = req.getParameter("in_out");
		String category = req.getParameter("category");
		String memo = req.getParameter("memo");
		String money = req.getParameter("money");


		//バリデーションチェック
		List<String> errors = validate(id, dating, inOut, category, memo, money);
		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			//session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = DBUtils.getConnection();

			sql = "update myhab set dating = ?, in_out = ?, category = ?, memo = ?, money = ? where id = ?;";
			ps = con.prepareStatement(sql);

			//insert命令にポストデータの内容をセット
			ps.setString(1, dating);
			ps.setString(2, inOut);
			ps.setString(3, category);
			ps.setString(4, memo);
			ps.setString(5, money);
			ps.setString(6, id);
System.out.println(ps);
			ps.executeUpdate();

			List<String> successes = new ArrayList<>();
			successes.add("更新しました。");
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


	private List<String> validate(String id, String dating, String inout, String category, String memo, String money) {
		List<String> errors = new ArrayList<>();
		
		//日付の必須入力
		if (dating.equals("")) {
			errors.add("日付は必須入力です。");
		}

		//カテゴリーの必須入力
		if (!category.equals("1") && !category.equals("2")) {
			errors.add("カテゴリーは必須入力です。");
		}

		//金額の必須入力
		if (money.equals("")) {
			errors.add("金額は必須入力です。");
		}

		return errors;
		
	}
}