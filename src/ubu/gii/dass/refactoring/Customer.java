package ubu.gii.dass.refactoring;

/**
* Tema  Refactorizaciones 
*
* Ejemplo de aplicaci�n de refactorizaciones. Actualizado para colecciones gen�ricas de java 1.5
*
* @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos L�pez</A>
* @version 1.1
* @see java.io.File
*
*/
import java.util.*;

public class Customer {
	private String _name;
	private List<Rental> _rentals;

	public Customer(String name) {
		_name = name;
		_rentals = new ArrayList<Rental>();

	};

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	};

	public String statement(boolean textStatement) {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();
		String result;
		if (textStatement) {
			result = "Rental Record for " + getName() + "\n";
		} else {
			result = "<h1>Rental Record for " + getName() + "</h1><br>";
		}
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();
			// determine amounts for each line
			thisAmount = each.calculatePrice(thisAmount);

			// add frequent renter points
			frequentRenterPoints = each.calculateFrecuency(frequentRenterPoints);
			// show figures for this rental
			if (textStatement) {
				result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			} else {
				result += "<p>&emsp" + each.getMovie().getTitle() + "&emsp" + String.valueOf(thisAmount) + "</p><br>";
			}
			totalAmount += thisAmount;
		}
		// add footer lines
		if (textStatement) {
			result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
			result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		} else {
			result += "<p>Amount owed is " + String.valueOf(totalAmount) + "</p><br>";
			result += "<p>You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points</p>";
		}
		return result;
	}
}
