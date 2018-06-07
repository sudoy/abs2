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
import javax.servlet.http.HttpSession;

import abs2.utils.DBUtils;

@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		
		String id = req.getParameter("id");
		
		//バリデーションチェック
		List<String> errors = validate(id);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			resp.sendRedirect("index.html");
			return;
		}

		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "delete from myhab where id = ?";
			//select命令の準備
			ps = con.prepareStatement(sql);

			//select文にパラメータの内容をセット
			ps.setString(1, id);

			//select命令を実行
			ps.executeUpdate();
			
			//成功メッセージ
			List<String> successes = new ArrayList<>();
			
			successes.add("削除しました。");
			session.setAttribute("successes", successes);

			resp.sendRedirect("index.html");

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
	private List<String> validate(String id) {
		List<String> errors = new ArrayList<>();
		
		//日付の必須入力
		if (id == null || id.equals("")) {
			errors.add("不正なアクセスです。");
		}
		return errors;
	}
}
