package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulator {
	
	private Model model;
	
	private PriorityQueue<Event> queue;
	
	private List<FoodCalories> foods;
	
	private List<Food> processati;
	private double tempo;
	
	private Integer k;
	
	public void inizializzazione(Integer k, Food food, Model model) {
		this.processati = new ArrayList<>();
		this.queue = new PriorityQueue<>();
		this.k = k;
		this.model = model;
		this.tempo = 0;
		this.foods = this.model.getFoodsForK(food);
		
		for(int i=0; i<k && i<foods.size(); i++) {
			this.queue.add(new Event(this.foods.get(i).getFood(), this.foods.get(i).getPeso()));
			processati.add(this.foods.get(i).getFood());
		}
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}

	public void processEvent(Event e) {
		this.foods = this.model.getFoodsForK(e.getFood());
		Event temp = null;
		this.tempo = e.getTime();
		
		for(FoodCalories fc : foods) {
			if(!this.processati.contains(fc.getFood())) {
				this.processati.add(fc.getFood());
				temp = new Event(fc.getFood(), fc.getPeso());
				temp.setTime(e.getTime()+fc.getPeso());
				this.queue.add(temp);
				return;
			}
		}	
	}
	
	public double getTotalTime() {
		return this.tempo;
	}
	
	public Integer getNumProcessati() {
		return this.processati.size();
	}

}
