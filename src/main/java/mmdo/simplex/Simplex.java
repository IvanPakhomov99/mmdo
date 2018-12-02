package mmdo.simplex;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implemented Simplex algorithm.
 * 
 * @author Anna_Bohun
 **/
public class Simplex {
	/** tableaux **/
	private double[][] tableaux;
	/** number of constraints **/
	private int numberOfConstraints;
	/** number of original variables **/
	private int numberOfOriginalVariables;
	private boolean maximizeOrMinimize;
	private static final boolean MAXIMIZE = true;

	/** basis[i] = basic variable corresponding to row i **/
	private int[] basis;

	public Simplex(double[][] tableaux, int numberOfConstraint, int numberOfOriginalVariable,
			boolean maximizeOrMinimize) {
		this.maximizeOrMinimize = maximizeOrMinimize;
		this.numberOfConstraints = numberOfConstraint;
		this.numberOfOriginalVariables = numberOfOriginalVariable;
		this.tableaux = tableaux;
		basis = new int[numberOfConstraints];
		for (int i = 0; i < numberOfConstraints; i++) {
			basis[i] = numberOfOriginalVariables + i;
		}

	}

	/**
	 * Runs simplex algorithm starting from initial BFS.
	 **/
	private void solve() {
		while (true) {
			int q = 0;
			// find entering column q
			if (maximizeOrMinimize) {
				q = dantzig();
			} else {
				q = dantzigNegative();
			}
			if (q == -1)
				break; // optimal
			// find leaving row p
			int p = minRatioRule(q);
			if (p == -1)
				throw new ArithmeticException("Linear program is unbounded");
			// pivot
			pivot(p, q);
			// update basis
			basis[p] = q;

		}
	}

	/**
	 * Index of a non-basic column with most positive cost
	 **/
	private int dantzig() {
		int q = 0;
		for (int j = 1; j < numberOfConstraints + numberOfOriginalVariables; j++)
			if (tableaux[numberOfConstraints][j] > tableaux[numberOfConstraints][q])
				q = j;
		if (tableaux[numberOfConstraints][q] <= 0) {
			return -1;
		} else {
			return q;
		}
	}

	/**
	 * Index of a non-basic column with most negative cost
	 **/
	private int dantzigNegative() {
		int q = 0;
		for (int j = 1; j < numberOfConstraints + numberOfOriginalVariables; j++)
			if (tableaux[numberOfConstraints][j] < tableaux[numberOfConstraints][q])
				q = j;

		if (tableaux[numberOfConstraints][q] >= 0)
			return -1; // optimal
		else
			return q;
	}

	/**
	 * Finds row p using min ratio rule (-1 if no such row)
	 **/
	private int minRatioRule(int q) {
		int p = -1;
		for (int i = 0; i < numberOfConstraints; i++) {
			if (tableaux[i][q] <= 0)
				continue;
			else if (p == -1)
				p = i;
			else if ((tableaux[i][numberOfConstraints + numberOfOriginalVariables]
					/ tableaux[i][q]) < (tableaux[p][numberOfConstraints + numberOfOriginalVariables] / tableaux[p][q]))
				p = i;
		}
		return p;
	}
	public void show() {
		  System.out.println("M = " + numberOfConstraints);
		  System.out.println("N = " + numberOfOriginalVariables);
		  for (int i = 0; i <= numberOfConstraints; i++) {
		   for (int j = 0; j <= numberOfConstraints
		     + numberOfOriginalVariables; j++) {
		    System.out.printf("%7.2f ", tableaux[i][j]);
		   }
		   System.out.println();
		  }
		  System.out.println("value = " + value());
		  for (int i = 0; i < numberOfConstraints; i++)
		   if (basis[i] < numberOfOriginalVariables)
		    System.out.println("x_"
		      + basis[i]
		      + " = "
		      + tableaux[i][numberOfConstraints
		        + numberOfOriginalVariables]);
		  System.out.println();
		 }
	/** Pivot on entry (p, q) using Gauss-Jordan elimination **/
	private void pivot(int p, int q) {
		// everything but row p and column q
		for (int i = 0; i <= numberOfConstraints; i++)
			for (int j = 0; j <= numberOfConstraints + numberOfOriginalVariables; j++)
				if (i != p && j != q)
					tableaux[i][j] -= tableaux[p][j] * tableaux[i][q] / tableaux[p][q];
		// zero out column q
		for (int i = 0; i <= numberOfConstraints; i++)
			if (i != p)
				tableaux[i][q] = 0.0;
		// scale row p
		for (int j = 0; j <= numberOfConstraints + numberOfOriginalVariables; j++)
			if (j != q)
				tableaux[p][j] /= tableaux[p][q];
		tableaux[p][q] = 1.0;
	}

	/** Return optimal objective value **/
	public double value() {
		return -tableaux[numberOfConstraints][numberOfConstraints + numberOfOriginalVariables];
	}

	/** Return primal solution vector **/
	public double[] primal() {
		double[] x = new double[numberOfOriginalVariables];
		for (int i = 0; i < numberOfConstraints; i++)
			if (basis[i] < numberOfOriginalVariables)
				x[basis[i]] = tableaux[i][numberOfConstraints + numberOfOriginalVariables];
		return x;
	}
	public Simplex() {}
	public  List<Double> getDecision(double[] objectiveFunc,double[][] constraintLeftSide, double[] constraintRightSide ) {
		Constraint[] constraintOperator = { Constraint.lessThan, Constraint.lessThan, Constraint.lessThan,
				Constraint.lessThan };
		Modeler model = new Modeler(constraintLeftSide, constraintRightSide, constraintOperator, objectiveFunc);
		Simplex simplex = new Simplex(model.getTableaux(), model.getNumberOfConstraint(),
				model.getNumberOfOriginalVariable(), MAXIMIZE);
		simplex.solve();
		simplex.show();
		List<Double> res = new ArrayList<Double>();
		double[] x = simplex.primal();
		for (double d : x) {
			res.add(d);
		}
		res.add(simplex.value());
		return res;
	}
}