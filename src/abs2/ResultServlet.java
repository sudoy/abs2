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

import abs2.beans.Myhab;
import abs2.utils.DBUtils;

@WebServlet("/result.html")
public class ResultServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();


		String dating1 = req.getParameter("dating1");
		String dating2 = req.getParameter("dating2");
		String category = req.getParameter("category");
		String memo = req.getParameter("memo");
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();
			// SQL
			sql = "SELECT m.id, m.dating, m.in_out, c.category_name, m.memo, m.money FROM myhab m LEFT JOIN categorylist c ON m.category = c.category_id where 0 = 0 ";

			// where句に代入
			if (!dating1.equals("") && !dating2.equals("")) {
				sql += " and dating between '" + dating1 + "' and '" + dating2 + "'";
			}else if(dating1.equals("") && !dating2.equals("")){
				sql += " and dating <= '" + dating2 + "'";
			}else if(!dating1.equals("") && dating2.equals("")) {
				sql += " and dating >= '" + dating1 + "'";
			} else {
				sql += "";
			}

//			if(category.equals("1") $$ !category2.equals("") $$ !category.equals("3")) {
//				sql += " and category=1";
//			}else if(category.equals("2")){
//				sql += " and category=2";
//
//			}

			if (memo != "") {
				sql += " and memo like '%" + memo + "%'";
			} else {
				sql += "";
			}
			
			sql += "order by dating";
			
			ps = con.prepareStatement(sql);
			
			System.out.println(ps);
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

			session.setAttribute("list", list);

			getServletContext().getRequestDispatcher("/WEB-INF/result.jsp")
			.forward(req, resp);

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
}