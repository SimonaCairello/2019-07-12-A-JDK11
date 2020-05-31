package it.polito.tdp.food.model;

public class FoodCalories implements Comparable<FoodCalories>{
	
	private Food food;
	private Double peso;
	
	public FoodCalories(Food food, double peso) {
		this.food = food;
		this.peso = peso;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
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
		result = prime * result + ((food == null) ? 0 : food.hashCode());
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
		FoodCalories other = (FoodCalories) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return food + " - " + peso;
	}

	@Override
	public int compareTo(FoodCalories o) {
		if(this.peso.compareTo(o.peso) == 0)
			return this.food.getDisplay_name().compareTo(o.getFood().getDisplay_name());
		else return -(this.peso.compareTo(o.peso));
	}

}
