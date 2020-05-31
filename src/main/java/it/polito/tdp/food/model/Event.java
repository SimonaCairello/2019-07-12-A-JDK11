package it.polito.tdp.food.model;

public class Event implements Comparable<Event>{
	
	private Food food;
	private double time;
	
	public Event(Food food, double time) {
		this.food = food;
		this.time = time;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		long temp;
		temp = Double.doubleToLongBits(time);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Event other = (Event) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		if (Double.doubleToLongBits(time) != Double.doubleToLongBits(other.time))
			return false;
		return true;
	}

	@Override
	public int compareTo(Event o) {
		return (int) (this.getTime()-o.getTime());
	}
	
	

}
