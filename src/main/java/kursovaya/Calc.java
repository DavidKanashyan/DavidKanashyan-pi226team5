package kursovaya;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Calc", urlPatterns = "/Calc")
public class Calc extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestCalc Calc = RequestCalc.fromRequestParameters(request);
		Calc.setAsRequestAttributesAndCalculate(request);

		request.getRequestDispatcher("/Results.jsp").forward(request, response);
	}

	private static class RequestCalc {
		private final String first_calc;
		private final String second_calc;
		private double result;

		private RequestCalc(String first, String second) {
			this.first_calc = first;
			this.second_calc = second;
		}

		public static RequestCalc fromRequestParameters(HttpServletRequest request) {
			return new RequestCalc(request.getParameter("first"), request.getParameter("second"));
		}

		static double getValue(double value) {
			if (value >= 0)
				return value;

			else
				return 0; // or throw an exception
		}

		public void setAsRequestAttributesAndCalculate(HttpServletRequest request) {
			request.setAttribute("first", first_calc);
			request.setAttribute("second", second_calc);
			double first_try;
			double second_try;
			double res;

			try {

				first_try = getValue(Double.parseDouble(first_calc));
				second_try = getValue(Double.parseDouble(second_calc));

			} catch (NumberFormatException e) {

				first_try = 0;
				second_try = 0;
				result = 0;
			}
			if (first_try >= 0 || second_try >= 0) {
				res = (first_try * second_try) / 2;
				result = res;
				request.setAttribute("result", res + " мм.");
			} else {
				res = 0;
				result = res;
				request.setAttribute("result", res + " мм.");
			}
			nasled klk = new nasled(first_try, second_try);
			result = klk.getRes();
			request.setAttribute("result", result + " мм.");

		}
	}

}