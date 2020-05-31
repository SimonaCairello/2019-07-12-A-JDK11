package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private FoodDao dao;
	private Graph<Food, DefaultWeightedEdge> graph;
	private Map<Integer, Food> vertices;
	private List<CoppiaFood> edges;
	private Simulator sim;

	public Model() {
		this.dao = new FoodDao();
		this.sim = new Simulator();
	}
	
	public Map<Integer, Food> getVertices(Integer portions) {
		this.vertices = this.dao.getVertices(portions);
		return this.vertices;
	}
	
	public void generateGraph() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.edges = this.dao.getEdges(vertices);
		
		Graphs.addAllVertices(this.graph, vertices.values());
		
		for(CoppiaFood cf : edges) {
			Graphs.addEdge(this.graph, cf.getFood1(), cf.getFood2(), cf.getPeso());
		}
	}
	
	public List<FoodCalories> getFood(Food food) {
		List<FoodCalories> foods = new ArrayList<>();
		List<FoodCalories> fiveFoods = new ArrayList<>();
		
		for(CoppiaFood c : edges) {
			if(c.getFood1().equals(food)) {
				foods.add(new FoodCalories(c.getFood2(), c.getPeso()));
			}
			if(c.getFood2().equals(food)) {
				foods.add(new FoodCalories(c.getFood1(), c.getPeso()));
			}				
		}
		
		Collections.sort(foods);
		
		for(int i=0; i<5; i++)
			fiveFoods.add(foods.get(i));
		
		return fiveFoods;
	}
	
	public List<FoodCalories> getFoodsForK(Food food) {
		List<FoodCalories> foods = new ArrayList<>();
		
		for(CoppiaFood c : edges) {
			if(c.getFood1().equals(food)) {
				foods.add(new FoodCalories(c.getFood2(), c.getPeso()));
			}
			if(c.getFood2().equals(food)) {
				foods.add(new FoodCalories(c.getFood1(), c.getPeso()));
			}				
		}
		
		Collections.sort(foods);
		
		return foods;
	}
	
	public void simula(Integer k, Food food) {
		if(this.graph!=null) {
			this.sim.inizializzazione(k, food, this);
		}
		
		this.sim.run();
	}
	
	public double getTotalTime() {
		return this.sim.getTotalTime();
	}
	
	public Integer getNumProcessati() {
		return this.sim.getNumProcessati();
	}
	
	public Integer getNumVert() {
		return this.vertices.size();
	}
	
	public Integer getNumArc() {
		return this.edges.size();
	}
}
