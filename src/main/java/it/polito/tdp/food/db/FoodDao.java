package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.CoppiaFood;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDao {
	public List<Food> listAllFoods() {
		String sql = "SELECT * FROM food";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			
			List<Food> list = new ArrayList<>();
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_code"), res.getString("display_name")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiments() {
		String sql = "SELECT * FROM condiment";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			
			List<Condiment> list = new ArrayList<>();
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"), res.getString("display_name"), res.getDouble("condiment_calories"), res.getDouble("condiment_saturated_fats")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Portion> listAllPortions() {
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"), res.getDouble("portion_amount"), res.getString("portion_display_name"), res.getDouble("calories"), res.getDouble("saturated_fats"), res.getInt("food_code")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public Map<Integer, Food> getVertices(Integer portions) {
		String sql = "SELECT food.food_code AS code, food.display_name AS name, count(DISTINCT p.portion_id) AS n " + 
				"FROM food, `portion` AS p " + 
				"WHERE food.food_code = p.food_code " + 
				"GROUP BY food.food_code " + 
				"HAVING n = ?";
		
		Map<Integer, Food> list = new HashMap<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, portions);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				try {
					list.put(res.getInt("code"), new Food(res.getInt("code"), res.getString("name")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<CoppiaFood> getEdges(Map<Integer, Food> vertices) {
		String sql = "SELECT f1.food_code AS fc1, f2.food_code AS fc2, AVG(c.condiment_calories) AS peso " + 
				"FROM food_condiment AS f1, condiment AS c, food_condiment AS f2 " + 
				"WHERE f1.condiment_code = f2.condiment_code AND f1.condiment_code = c.condiment_code AND f1.food_code > f2.food_code " + 
				"GROUP BY f1.food_code, f2.food_code";
		
		List<CoppiaFood> list = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				if(vertices.get(res.getInt("fc1"))!=null && vertices.get(res.getInt("fc2"))!=null)
					list.add(new CoppiaFood(vertices.get(res.getInt("fc1")), vertices.get(res.getInt("fc2")), res.getDouble("peso")));
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
}
