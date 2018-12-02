package mmdo.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mmdo.transport_problem.TransportProblem;
import mmdo.transport_problem.Variable;

/**
 * Servlet implementation class TransportProblemServlet
 */

public class TransportProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("transport.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TransportProblem problem = new TransportProblem(3, 4);
		double[] stockArray = new double[3];
		double[] requiredArray = new double[4];
		double[][] costMatrix = new double[3][4];
		int method = request.getParameter("method").equals("northWestCorner") ? 1 : 0;
		double target = 0;
		for (int i = 1; i < 3; i++) {
			stockArray[i-1] = Double.parseDouble(request.getParameter("bakery" + i));
		}
		for (int i = 1; i <= 4; i++) {
			requiredArray[i-1] = Double.parseDouble(request.getParameter("factory" + i));
		}
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 4; j++) {
				costMatrix[i-1][j-1] = Double.parseDouble(request.getParameter(i+""+j));
			}
		}
		List<Variable> result = problem.solve(stockArray, requiredArray, costMatrix, method, target);
		request.setAttribute("list", result);
		request.setAttribute("target", target);
		request.getRequestDispatcher("transport_result.jsp").forward(request, response);
	}

}
