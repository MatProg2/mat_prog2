public class Complexo21 {
	
	private double re, im;

	public Complexo21(double real, double imag) {
		re = real;
		im = imag;
	}

	public double real() {
		return re;
	} 

	public double imag() {
		return im;
	} 

	public double abs() {
		return Math.sqrt(re * re + im * im);
	} 

	public double phase() {
		return Math.atan2(im, re);
	} 

}