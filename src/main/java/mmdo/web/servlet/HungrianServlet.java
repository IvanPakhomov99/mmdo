package mmdo.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mmdo.hungrian.HungarianAlgorithm;


/**
 * Servlet implementation class HungrianServlet
 */
public class HungrianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> cities;
	List<String> managers;
	int number;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HungrianServlet() {
		cities = new ArrayList<String>();
		cities.add("Харьков(1)");
		cities.add("Днепр(2)");
		cities.add("Львов(3)");
		cities.add("Киев(4)");
		cities.add("Мерефа(5)");
		managers = new ArrayList<String>();
		managers.add("По финансам(1)");
		managers.add("По маркетингу(2)");
		managers.add("По производству(3)");
		managers.add("По персоналу(4)");
		managers.add("По инвестициям(5)");
		number = 1;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("cities", cities);
		request.setAttribute("managers", managers);
		request.setAttribute("number", number);
		request.getRequestDispatcher("hungrian.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double[][] costMatrix = new double[5][5];
		double mark = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				costMatrix[i][j] = Double.parseDouble(request.getParameter(i + "" + j));
	
			}
		}
		HungarianAlgorithm alg = new HungarianAlgorithm(costMatrix);
		int[] decision = alg.execute();
		for (int i = 0; i < decision.length; i++) {
				mark+=costMatrix[i][decision[i]] ;
			
		}
		request.setAttribute("cities", cities);
		request.setAttribute("managers", managers);
		request.setAttribute("decision", decision);
		request.setAttribute("mark", mark);
		request.setAttribute("number", number);
		request.getRequestDispatcher("h_result.jsp").forward(request, response);
	}

}
