package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class webController implements Initializable{
    int index=-1;
    @FXML
    private Pane pane;

    @FXML
    private TableView<Data> tabview;

    @FXML
    private TableColumn<Data, Integer> colID;

    @FXML
    private TableColumn<Data, String> colfname;

    @FXML
    private TableColumn<Data, String> collast;

    @FXML
    private TableColumn<Data, String> coladdress;
    @FXML
    private TextField txtID;

    @FXML
    private TextField txtlname;

    @FXML
    private TextField txtadr;

    @FXML
    private Label datalabel;
    @FXML
    private Line line;
    @FXML
    private TextField txtfname;
    @FXML
    private TextField txtsearch;
    public Data getinput() {
    	if(txtID.getText().equals("")) {
    		txtID.setPromptText("THIS FIELD IS REQUIRED");
    		return null;
    	}
    	int id = Integer.parseInt(txtID.getText());
    	//the ID should be unique
        for(int i=0;i<list.size();i++) {
     	   if(list.get(i).getId() == id) {
     	  txtID.setText("");
   		   txtID.setPromptText("the id is already used");
   		   txtlname.setText("");
   		   txtfname.setText("");
   		   txtadr.setText("");
     			  
     		   return null;
     	   }
        }
        //let s get the text from the textfields and put it in the tableview
        String first=txtfname.getText();
        String last=txtlname.getText();
        String adr=txtadr.getText();
        return new Data(id,first,last,adr);
        
    }
    @FXML
    void create(ActionEvent event) {
       
    	if(getinput()==null) {
    	   return;
    	 }
       list.add(getinput());
       tabview.setItems(list);
       //set the textfields to blank again
       txtID.setText("");
       txtID.setPromptText("");
	   txtlname.setText("");
	   txtfname.setText("");
	   txtadr.setText("");
    }
//we need to detect the selection of a row in our tableview
    @FXML
    void row_selected(MouseEvent event) {
    	  index=tabview.getSelectionModel().getSelectedIndex();
    	 if(index <=-1) return;//the first row's index is 0 .
    	 //the index will allow us to acces the content of each column  of the table
    	   txtID.setText(colID.getCellData(index).toString());
    	   txtlname.setText(colfname.getCellData(index).toString());
    	   txtfname.setText(collast.getCellData(index).toString());
    	   txtadr.setText(coladdress.getCellData(index).toString());
    	   
    }
    @FXML
    void delete(ActionEvent event) {
     switch(index) {
         case -1 : return;
         default : tabview.getItems().remove(index);
                   index = -1;
                   break;
     }
    	
      
    }

    @FXML
    void edit(ActionEvent event) {
switch(index) {
   case -1:           return;
             
             
          
   default:      if(txtID.getText().equals("")) {
		             txtID.setPromptText("THIS FIELD IS REQUIRED");
		                return ;
	                        }  
	              int id = Integer.parseInt(txtID.getText());
                 //the ID should be unique
                   for(int i=0;i<list.size();i++) {
                           if(list.get(i).getId() == id && i!=index) {
  	                              txtID.setText("");
		                            txtID.setPromptText("the id is already used");
		                            txtlname.setText("");
		                          txtfname.setText("");
		                            txtadr.setText("");
		                        return;
	     	       }
}
//let s get the text from the textfields and put it in the tableview
	   String first=txtfname.getText();
	   String last=txtlname.getText();
	   String adr=txtadr.getText(); 
 list.set(index, new Data(id,first,last,adr));
 tabview.setItems(list);
    //set the textfields to blank again
       txtID.setText("");
       txtID.setPromptText("");
	   txtlname.setText("");
	   txtfname.setText("");
	   txtadr.setText("");
    index=-1; 
    break;
	
}
 
    }

    @FXML
    void search(KeyEvent event) {
     txtsearch.textProperty().addListener(new InvalidationListener() {

		@Override
		public void invalidated(Observable arg0) {
			if(txtsearch.getText().isEmpty()) {
				tabview.getItems().removeAll();
				tabview.setItems(list);
			}
		 ObservableList<Data> list_search = FXCollections.observableArrayList();
		 ObservableList<TableColumn<Data,?>> column = tabview.getColumns(); 
		  for(int row =0, col;row<list.size();row++) {
		     for(col =0;col<column.size();col++) {
		     TableColumn colvar=column.get(col);
		     String cell= colvar.getCellData(list.get(row)).toString();
		    if( cell.contains(txtsearch.getText())  && cell.startsWith(txtsearch.getText())) { 		  
		    	list_search.add(list.get(row));	  
		    	tabview.setItems(list_search);
		    	break;
		    }
		    	  }
		      }
		}
    	 
     });
     
      
    }
    ObservableList<Data> list = FXCollections.observableArrayList(
			  new Data(1,"yassin","jama","xxxx@xx"),
			  new Data(2,"ya3in","jawma","xxxx@xx"),
			  new Data(3,"karim","khals","xxxx@xx")	  
					
  		);
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
   colID.setCellValueFactory(new PropertyValueFactory<Data,Integer>("id"));
   colfname.setCellValueFactory(new PropertyValueFactory<Data,String>("first_name"));
   collast.setCellValueFactory(new PropertyValueFactory<Data,String>("last_name"));
   coladdress.setCellValueFactory(new PropertyValueFactory<Data,String>("adress"));
   tabview.setItems(list);
   TranslateTransition t = new TranslateTransition (Duration.seconds(6),pane);
   
   t.setToY(pane.getLayoutY()-400);
   
   TranslateTransition tlabel = new TranslateTransition (Duration.seconds(6),datalabel);
   TranslateTransition tline = new TranslateTransition (Duration.seconds(6),line);
   tline.setToX(line.getLayoutX()+190);
   tlabel.setToX(datalabel.getLayoutX()+350);
   tline.play();
   tlabel.play();
   t.play();
	}

}
