package kursovaya;

public class nasled extends Parametr {

	public double res = 0;

public nasled(Double first, Double second) {
res = mathemat(first, second);
}

	public double getRes() {
		return res;
	}
}