import java.text.DecimalFormat;
public class Triangle {

	//instance variables
	private double sideA = DEFAULT_SIDE;
	private double sideB = DEFAULT_SIDE;
	private double sideC = DEFAULT_SIDE;
	
	//static variables
	public static final String POLYGONSHAPE = "Triangle";
	public static final double DEFAULT_SIDE = 1;
	
	//Helper Methods
	public static boolean isTriangle(double sideA, double sideB, double sideC)
	{
		
		if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
			return false;
		}
		boolean conA = false;
		boolean conB = false;
		boolean conC = false;
		if (sideA + sideB > sideC) {
			conA = true;
		}
		if (sideB + sideC > sideA){
			conB = true;
		}
		if (sideC + sideA > sideB) {
			conC = true;
		}
		
		if (conA == true && conB == true && conC == true) {
			return true;
		}
		return false;
	}
	
	public static double lawOfCosines(double a, double b, double c)
	{
		double numer = (a*a + b*b - (c*c));
		double denom = (2*a*b);
		double intAngle = Math.toDegrees(Math.acos((numer/denom)));
		return intAngle;
		//Returns the angle opposite of c
	}
	
	public static boolean isTriangle(double [] sides) 
	{
		if (sides == null) {
			return false;
		}
		
		if (sides.length != 3) {
			return false;
		}
		
		boolean passesTest = isTriangle(sides[0], sides[1], sides[2]);
		return passesTest;
	}
	//Constructors
	Triangle()
	{
		sideA = DEFAULT_SIDE;
		sideB = DEFAULT_SIDE;
		sideC = DEFAULT_SIDE;
	}
	
	Triangle( double sideA, double sideB, double sideC)
	{
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
		
		/*if (sideA < 0)
		{
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}
		if (sideB < 0) {
			this.sideB = DEFAULT_SIDE;
			this.sideA = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}
		if (sideC < 0) {
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE; 
		} */
		boolean letsSee = isTriangle(sideA, sideB, sideC);
		
		if (letsSee == false) {
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}
	}
	
	Triangle (double [] sides)
	{
		boolean letsSee = isTriangle(sides);
		
		if (letsSee == false) {
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;	
		}
		
		sideA = sides[0];
		sideB = sides[1];
		sideC = sides[2];
		
	}
	
	Triangle (Triangle triangle)
	{
		boolean letsSee = isTriangle(sideA, sideB, sideC);
		
		if (letsSee == false) {
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;	
		}
		
		if (sideA < 0)
		{
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}
		if (sideB < 0) {
			this.sideB = DEFAULT_SIDE;
			this.sideA = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}
		if (sideC < 0) {
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}
		
	}

	//Getters and Setters
	public double getSideA() {
		return sideA;
	}

	public boolean setSideA(double sideA) {
		
		if (sideA <= 0) {
			return false;
		}
		
		this.sideA = sideA;
		
		boolean letsSee = isTriangle(sideA, sideB, sideC);
		return letsSee;
	}

	public double getSideB() {
		return sideB;
	}

	public boolean setSideB(double sideB) {
		
		if (sideB <= 0) {
			return false;
		}
		
		this.sideB = sideB;
		
		boolean letsSee = isTriangle(sideA, sideB, sideC);
		return letsSee;
	}

	public double getSideC() {
		return sideC;
	}

	public boolean setSideC(double sideC) {
		if (sideC <= 0) {
			return false;
		}
		
		this.sideC = sideC;
		
		boolean letsSee = isTriangle(sideA, sideB, sideC);
		return letsSee;
	}
	
	public double [] getSides() {
		
		double [] array = {sideA, sideB, sideC};
		return array;
	}
	
	public boolean setSides(double []sides) {
		
		boolean letsSee = isTriangle(sides);
		
		if (letsSee == false) {
			return false;
		}
		
		/*if (sideA < 0)
		{
			return false;
		}
		if (sideB < 0) {
			return false;
		}
		if (sideC < 0) {
			return false;
		} */
		
		sideA = sides[0];
		sideB = sides[1];
		sideC = sides[2];

		return true;
	}
	
	public double getAngleA() {
		
		double intAngleA = lawOfCosines(sideC, sideB, sideA);
		return intAngleA;
	}
	
	public double getAngleB() {
		
		double intAngleB = lawOfCosines(sideA, sideC, sideB);
		return intAngleB;
	}
	
	public double getAngleC() {
		
		double intAngleC = lawOfCosines(sideA, sideB, sideC);
		return intAngleC;
	}
	
	public double [] getAngles() {
		
		double firstElement = getAngleA();
		double secondElement = getAngleB();
		double thirdElement = getAngleC();
		
		double [] array = {firstElement, secondElement, thirdElement};
		return array;
	}

	@Override
	public String toString() {
		DecimalFormat nowRounded = new DecimalFormat("#.####");
		return "Triangle (" + nowRounded.format(sideA) + ", " + nowRounded.format(sideB) + ", " + nowRounded.format(sideC) + ")";
	}
	
	
}
