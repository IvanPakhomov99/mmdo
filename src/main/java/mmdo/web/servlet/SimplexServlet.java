package mmdo.web.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mmdo.simplex.Simplex;

/**
 * Servlet implementation class SimplexServlet
 */
public class SimplexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("simplex.html").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Simplex simplex = new Simplex();
		double[] objectiveFunc = { Double.parseDouble(request.getParameter("profit1")),
				Double.parseDouble(request.getParameter("profit2")),
				Double.parseDouble(request.getParameter("profit3")),
				Double.parseDouble(request.getParameter("profit4")) };
		double[][] constraintLeftSide = {
				{ Double.parseDouble(request.getParameter("fertilizer1")),
						Double.parseDouble(request.getParameter("fertilizer2")),
						Double.parseDouble(request.getParameter("fertilizer3")),
						Double.parseDouble(request.getParameter("fertilizer4")) },
				{ Double.parseDouble(request.getParameter("square1")),
						Double.parseDouble(request.getParameter("square2")),
						Double.parseDouble(request.getParameter("square3")),
						Double.parseDouble(request.getParameter("square4")) },
				{ Double.parseDouble(request.getParameter("amount1")),
						Double.parseDouble(request.getParameter("amount2")),
						Double.parseDouble(request.getParameter("amount3")),
						Double.parseDouble(request.getParameter("amount4")) } };
		double[] constraintRightSide = { Double.parseDouble(request.getParameter("fertilizer_all")),
				Double.parseDouble(request.getParameter("square_all")),
				Double.parseDouble(request.getParameter("amount_all")) };
		List<Double> list  = simplex.getDecision(objectiveFunc, constraintLeftSide, constraintRightSide);
		request.setAttribute("l1", list.get(0));
		request.setAttribute("l2", list.get(1));
		request.setAttribute("l3", list.get(2));
		request.setAttribute("l4", list.get(3));
		request.setAttribute("l5", list.get(4));
		request.getRequestDispatcher("simplex_result.jsp").forward(request, response);
	}

}
