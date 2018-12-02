package mmdo.transport_problem;

import java.util.LinkedList;
import java.util.List;

public class TransportProblem {

	private double[] required;
	private double[] stock;
	private double[][] cost;
	private List<Variable> feasible = new LinkedList<Variable>();

	public List<Variable> getFeasible() {
		return feasible;
	}

	public void setFeasible(List<Variable> feasible) {
		this.feasible = feasible;
	}

	private int stockSize;
	private int requiredSize;

	public TransportProblem(int stockSize, int requiredSize) {
		this.stockSize = stockSize;
		this.requiredSize = requiredSize;
		stock = new double[stockSize];
		required = new double[requiredSize];
		cost = new double[stockSize][requiredSize];
		for (int i = 0; i < (requiredSize + stockSize - 1); i++)
			feasible.add(new Variable());

	}

	public double[] getRequired() {
		return required;
	}

	public void setRequired(double[] required) {
		this.required = required;
	}

	public double[] getStock() {
		return stock;
	}

	public void setStock(double[] stock) {
		this.stock = stock;
	}

	public double[][] getCost() {
		return cost;
	}

	public void setCost(double[][] cost) {
		this.cost = cost;
	}

	/**
	 * initializes the feasible solution list using the North-West Corner
	 * 
	 * @return time elapsed
	 */

	public double northWestCorner() {
		long start = System.nanoTime();

		double min;
		int k = 0; // feasible solutions counter

		// isSet is responsible for annotating cells that have been allocated
		boolean[][] isSet = new boolean[stockSize][requiredSize];
		for (int j = 0; j < requiredSize; j++)
			for (int i = 0; i < stockSize; i++)
				isSet[i][j] = false;

		// the for loop is responsible for iterating in the 'north-west' manner
		for (int j = 0; j < requiredSize; j++)
			for (int i = 0; i < stockSize; i++)
				if (!isSet[i][j]) {

					// allocating stock in the proper manner
					min = Math.min(required[j], stock[i]);

					feasible.get(k).setRequired(j);
					feasible.get(k).setStock(i);
					feasible.get(k).setValue(min);
					k++;

					required[j] -= min;
					stock[i] -= min;

					// allocating null values in the removed row/column
					if (stock[i] == 0)
						for (int l = 0; l < requiredSize; l++)
							isSet[i][l] = true;
					else
						for (int l = 0; l < stockSize; l++)
							isSet[l][j] = true;
				}
		return (System.nanoTime() - start) * 1.0e-9;
	}

	/**
	 * initializes the feasible solution list using the Least Cost Rule
	 * 
	 * it differs from the North-West Corner rule by the order of candidate cells
	 * which is determined by the corresponding cost
	 * 
	 * @return double: time elapsed
	 */

	public double leastCostRule() {
		long start = System.nanoTime();
		double min;
		int k = 0; // feasible solutions counter
		// isSet is responsible for annotating cells that have been allocated
		boolean[][] isSet = new boolean[stockSize][requiredSize];
		for (int j = 0; j < requiredSize; j++)
			for (int i = 0; i < stockSize; i++)
				isSet[i][j] = false;

		int i = 0, j = 0;
		Variable minCost = new Variable();

		// this will loop is responsible for candidating cells by their least cost
		while (k < (stockSize + requiredSize - 1)) {

			minCost.setValue(Double.MAX_VALUE);
			// picking up the least cost cell
			for (int m = 0; m < stockSize; m++)
				for (int n = 0; n < requiredSize; n++)
					if (!isSet[m][n])
						if (cost[m][n] < minCost.getValue()) {
							minCost.setStock(m);
							minCost.setRequired(n);
							minCost.setValue(cost[m][n]);
						}

			i = minCost.getStock();
			j = minCost.getRequired();
			// allocating stock in the proper manner
			min = Math.min(required[j], stock[i]);
			feasible.get(k).setRequired(j);
			feasible.get(k).setStock(i);
			feasible.get(k).setValue(min);
			k++;
			required[j] -= min;
			stock[i] -= min;
			// allocating null values in the removed row/column
			if (stock[i] == 0)
				for (int l = 0; l < requiredSize; l++)
					isSet[i][l] = true;
			else
				for (int l = 0; l < stockSize; l++)
					isSet[l][j] = true;
		}
		return (System.nanoTime() - start) * 1.0e-9;
	}

	public double getSolution() {
		double result = 0;
		for (Variable x : feasible) {
			result += x.getValue() * cost[x.getStock()][x.getRequired()];
		}
		return result;
	}

	public List<Variable> solve(double[] stockArray, double[] requiredArray, double[][] costMatrix, int method,
			double target) {
		TransportProblem test = new TransportProblem(stockArray.length, requiredArray.length);
		test.setRequired(requiredArray);
		test.setStock(stockArray);
		test.setCost(cost);
		if (method == 1) {
			System.out.println("north");
			test.northWestCorner();
		} else {
			System.out.println("least");
			test.leastCostRule();
		}
		target = test.getSolution();
		return test.getFeasible();
	}
}
