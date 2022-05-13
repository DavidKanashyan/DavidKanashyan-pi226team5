package kursovaya;

public abstract class Parametr {
	private double result;

	public double mathemat(Double first, Double second) {
		result = (first * second) / 2;
		return result;
	}
}
