package abs2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abs2.utils.DBUtils;


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
		//HttpSession session = req.getSession();

		String  dating = req.getParameter("dating");
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
			successes.add("登録しました。");
			//session.setAttribute("successes", successes);
			req.setAttribute("successes", successes);

			//テスト用、実装時には下のsendredirect使用
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);

			//resp.sendRedirect("index.html");
			
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