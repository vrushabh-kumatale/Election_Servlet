package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String candId = req.getParameter("candidate");
		int cId = Integer.parseInt(candId);
		try(CandidateDao candDao = new CandidateDaoImpl()) {
			int cnt = candDao.incrementVote(cId);
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Voting Done</title>");
			out.println("</head>");
			out.println("<body>");
			
			if(cnt == 1)
				out.println("<h5>Your Vote Registered Successfully..</h5>");
			else
				out.println("<h5>Some error occured. Try again</h5>");
			out.println("<a href='logout'>Sign Out</a>");
						out.println("</body>");
					out.println("</html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
