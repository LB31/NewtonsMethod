import java.util.Arrays;

public class NewtonCalculator {

	private double startX;
	private double[] functionA = {-1, -1, 0, 0, 0, 0, 1};
	private double[] test = {0,0,1};
	private double endX;
	private int newtonRuns = 0;

	public NewtonCalculator() {

		endX = applyNewtonMethod(functionA);
		System.out.println(endX);
		System.out.println(newtonRuns);
		System.out.println(calculateYTest(functionA, endX));
		System.out.println(Math.pow(endX, 6));

		
//		double[] back = derivation(functionA);
		System.out.println(Arrays.toString(functionA));
//		System.out.println(calculateY(derivation(functionA), 4));
//		calculateStartX(test, 10);
//		System.out.println(startX);
	}

	public double[] derivation(double[] function) {
		double[] derivation = new double[function.length - 1];
		for (int i = 1; i < function.length; i++) {
			derivation[i - 1] = function[i] * i;
		}

		return derivation;
	}

	public double calculateY(double[] function, double inputX) {
		double resultY = 0;
//		System.out.println(function[function.length-1] * Math.pow(inputX, 6));
		for (int i = 0; i < function.length; i++) {
			resultY += function[i] * Math.pow(inputX, i);
//			System.out.println(resultY + " " + i);
		}

		return resultY;
	}

	
	public double calculateYTest(double[] function, double inputX) {
		double resultY = 0;
//		System.out.println(function[function.length-1] * Math.pow(inputX, 6));
		for (int i = 0; i < function.length; i++) {
			resultY += function[i] * Math.pow(inputX, i);
			System.out.println(resultY + " " + i);
		}

		return resultY;
	}
	
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

	
	
	
	public double applyNewtonMethod(double[] function){
		calculateStartX(function, 10);
		double zeroPoint;
		double previousStart;
		double currentStart = startX;
		
		double leftControl;
		double rightControl;
		do{
			previousStart = currentStart;
			currentStart = currentStart - calculateY(function, currentStart) / calculateY(derivation(function), currentStart);
			leftControl = (double) Math.round(previousStart * 100000) / 100000;

			rightControl = (double) Math.round(currentStart * 100000) / 100000;
			newtonRuns++;
		} while(leftControl != rightControl);
		
		
		return currentStart;
	}
	
	
	
	public static void main(String[] args) {
		new NewtonCalculator();

	}

}
