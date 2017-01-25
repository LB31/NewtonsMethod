
public class BisectionCalculator {

	
	
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
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
