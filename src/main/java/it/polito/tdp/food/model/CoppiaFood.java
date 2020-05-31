package it.polito.tdp.food.model;

public class CoppiaFood{
	
	private Food food1;
	private Food food2;
	private double peso;
	
	public CoppiaFood(Food food1, Food food2, double peso) {
		this.food1 = food1;
		this.food2 = food2;
		this.peso = peso;
	}

	public Food getFood1() {
		return food1;
	}

	public void setFood1(Food food1) {
		this.food1 = food1;
	}

	public Food getFood2() {
		return food2;
	}

	public void setFood2(Food food2) {
		this.food2 = food2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food1 == null) ? 0 : food1.hashCode());
		result = prime * result + ((food2 == null) ? 0 : food2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoppiaFood other = (CoppiaFood) obj;
		if (food1 == null) {
			if (other.food1 != null)
				return false;
		} else if (!food1.equals(other.food1))
			return false;
		if (food2 == null) {
			if (other.food2 != null)
				return false;
		} else if (!food2.equals(other.food2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return food1 + ", " + food2 + " - " + peso + "\n";
	}
}
