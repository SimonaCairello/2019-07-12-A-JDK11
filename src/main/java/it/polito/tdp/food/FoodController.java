/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.FoodCalories;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalorie"
    private Button btnCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	
    	String s = txtPorzioni.getText();
    	Integer portions = 0;
    	
    	try {
    		portions = Integer.parseInt(s);
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Inserisci un numero intero!\n");
    		return;
    	}
    	
    	if(portions<0) {
    		txtResult.appendText("Inserisci un numero maggiore di zero!\n");
    		return;
    	}
    	
    	Map<Integer, Food> food = new HashMap<>();
    	food = this.model.getVertices(portions);
    	
    	if(food.size()==0) {
    		txtResult.appendText("Il grafo è vuoto!\n");
    		return;
    	}
    	
    	this.model.generateGraph();
    	txtResult.appendText("Grafo creato con successo!\n");
    	
    	this.boxFood.getItems().setAll(food.values());
    }
    
    @FXML
    void doCalorie(ActionEvent event) {
    	txtResult.clear();
    	
    	if(this.boxFood.getValue()==null) {
    		txtResult.appendText("Selezionare un cibo!\n");
    		return;
    	}
    	
    	List<FoodCalories> fiveFoods = this.model.getFood(this.boxFood.getValue());
    	for(FoodCalories f : fiveFoods) {
    		txtResult.appendText(f.toString()+"\n");
    	}
    	
    	txtResult.appendText(this.model.getNumArc()+" "+this.model.getNumVert());
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	
    	Integer k = 0;
    	Food f = boxFood.getValue();
    	
    	try {
    		k = Integer.parseInt(txtK.getText());
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Inserisci un numero intero!\n");
    		return;
    	}
    	
    	this.model.simula(k, f);
    	
    	txtResult.appendText(this.model.getTotalTime()+"\n"+this.model.getNumProcessati());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
