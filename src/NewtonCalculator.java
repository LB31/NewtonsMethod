
import java.util.Arrays;

public class NewtonCalculator {

	private double startX;
	private double[] functionA = { -1, -1, 0, 0, 0, 0, 1 };
	private double[] functionC = { 2, -2, 0, 1 };
	private double[] test = { -8, 0, 1 };
	private double endX;
	private int newtonRuns = 0;

	public NewtonCalculator() {

		endX = applyNewtonMethod(functionC);
		// The zero point
		System.out.println(endX);
		// speed of convergence
		System.out.println(newtonRuns);
		// http://stackoverflow.com/a/20937683 print double value without
		// scientific notation
		// Test if you get a zero point back
		System.out.printf("%.9f", calculateY(functionC, endX));
		// The function
		System.out.println(Arrays.toString(functionC));

	}

	// f(x)
	public double calculateY(double[] function, double inputX) {
		double resultY = 0;

		for (int i = 0; i < function.length; i++) {
			resultY += function[i] * Math.pow(inputX, i);

			// resultY = roundToFive(resultY);

		}

		return resultY;
	}

	// f'(x)
	public double[] derivation(double[] function) {
		double[] derivation = new double[function.length - 1];
		for (int i = 1; i < function.length; i++) {
			derivation[i - 1] = function[i] * i;
		}

		return derivation;
	}

	// X0
	public void calculateStartX(double[] function, int runLimit) {
		double startValue = 0;
		double previousYValue = calculateY(function, -runLimit);
		double currentYValue;

		for (int i = -runLimit; i <= runLimit; i++) {
			currentYValue = calculateY(function, i);
			// Change of sign
			if (previousYValue < 0 ^ currentYValue < 0) {
				// check who is closer to zero
				if (Math.abs(previousYValue) > Math.abs(currentYValue)) {
					startValue = i;
				} else {
					startValue = i - 1;
				}
				i = runLimit + 1;
			}
			previousYValue = currentYValue;
		}

		startX = startValue;
	}

	// x0 - f(x0) / f'(x0)
	public double applyNewtonMethod(double[] function) {
		calculateStartX(function, 10);
		double zeroPoint;
		double previousStart;
		double currentStart = startX;

		double leftControl;
		double rightControl;
		do {
			previousStart = currentStart;
			// Newton's method
			currentStart = currentStart
					- calculateY(function, currentStart) / calculateY(derivation(function), currentStart);

			leftControl = roundToFive(previousStart);
			rightControl = roundToFive(currentStart);
			newtonRuns++;
		} while (leftControl != rightControl);

		return roundToFive(currentStart);
	}

	private double roundToFive(double resultY) {
		return (double) Math.round(resultY * 100000) / 100000;
	}

	public static void main(String[] args) {
		new NewtonCalculator();

	}

}
