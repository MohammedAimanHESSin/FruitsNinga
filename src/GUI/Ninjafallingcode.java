package GUI ;
import java.io.File;
import java.util.ArrayList;
import logic.*;
import factoryPattern.*;
import GUI.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Ninjafallingcode extends Application {
	    AnimationTimer timer;
	    double limit = 90;
	       int M =0;
	       int n =0,r=0,s=0;

	    Pane root = new Pane();
	    GameController controller =GameController.getInstance();
	   
	    //ArrayList<Double> orangeLimit = new ArrayList<Double>();
	    //MELON
	    //ArrayList<ImageView> melonList = new ArrayList<ImageView>();
	    

	    double speed;
	    double falling;
	    
	    public static void main(String[] args) {
	        launch();
	        
	    }
	 
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        speed = 0.0001;
	        falling = 3000;
	        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(falling), event -> { 
	        	 speed += 0.75;
	             controller.getOrangeList().add(controller.createGameObject("ORANGE", "fruit"));
	             createOrange((Orange)controller.getOrangeList().get(M));
	             clickOrange();

	             controller.getMelonList().add( controller.createGameObject("MELLON", "fruit"));
		      createMelon((Mellon)(controller.getMelonList().get(M++)));
		      clickMellon();
		         
	             root.getChildren().add(((Node)controller.getOrangeList().get(controller.getOrangeList().size()-1).getImageView()));
	             root.getChildren().add(((Node)controller.getMelonList().get(controller.getMelonList().size()-1).getImageView()));

	            
	        }));
	        
	        timeline.setCycleCount(5);

	       
	       
	        timeline.play();
	        timer = new AnimationTimer() {
	            @Override
	            public void handle(long arg0) {
	                gameUpdate(); 

	            }
	           
	        };
                        
	          
	        timer.start(); 
	        /*timeline.setOnFinished((event) -> {
		       // timeline.setDelay(new Duration(10000));

	        	// set alert type       
				 Alert a = new Alert(AlertType.INFORMATION);
				// set alert type       
	           // set content text 
	           a.setContentText("END OF GAME YOUR SCORE IS : [] "); 
	           // show the dialog 
	           a.show(); 
	        });*/
	        /*orangeList.get(0).getImageView().setOnMouseClicked((event) -> {
	            System.out.println("HERE !!!");
	        });*/
	       
	        Scene scene = new Scene(root, 1000, 600);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	    }
	  public void createMelon(GameObject mellon) {
	       
	             createOcject(mellon);
	    }
           public void createOrange(GameObject orange) 
                {
                    createOcject(orange);	    
                }
            public void createOcject(GameObject object)
            {
                object.setIntialImageView();
	        object.getImageView().setFitHeight(100);
	        object.getImageView().setFitWidth(100);
	        object.getImageView().setLayoutX(rand(100, 800));
	        object.getImageView().setLayoutY(600);
	        object.setLimit(rand(150, 290));
            }
	       
	   
	    public int rand(int min, int max) {
	        return (int)(Math.random() * max + min);
	    }
	    
             public void clickOrange(){
                click(controller.getOrangeList());
  
            }
            public void clickMellon()
            {
                System.out.println("clicked mwllon ");
		         click(controller.getMelonList());  
	    }
            
            public void click(ArrayList<GameObject> objectList)
            { objectList.forEach(o->{
            	o.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() 
            	{
            		@Override
                    public void handle(MouseEvent event) 
                    {controller.sliceObjects(o);}
            	});
            });
                 }
             public void objectUpdate(ArrayList<GameObject> objectList){
            for(int i = 0; i < objectList.size(); i++) {
	            if(((ImageView) objectList.get(i).getImageView()).getLayoutY() > objectList.get(i).getLimit())
	                ((ImageView) objectList.get(i).getImageView()).setLayoutY(((ImageView)objectList.get(i).getImageView()).getLayoutY() - speed - ((ImageView) objectList.get(i).getImageView()).getLayoutY() / 700 );
	            if(((ImageView) objectList.get(i).getImageView()).getLayoutY() <= objectList.get(i).getLimit()){
	                ((ImageView) objectList.get(i).getImageView()).setLayoutY(((ImageView) objectList.get(i).getImageView()).getLayoutY() + speed + ((ImageView) objectList.get(i).getImageView()).getLayoutY() / 700);
	                objectList.get(i).setLimit(((ImageView) objectList.get(i).getImageView()).getLayoutY());   
	            }
	        }
             }
	        
	   
	    
	    
	    public void gameUpdate(){
               objectUpdate (controller.getOrangeList());
                objectUpdate (controller.getMelonList());

            
                
	       /* for(int i = 0; i < controller.getMelonList().size(); i++) {
	            if(((ImageView) controller.getMelonList().get(i).getImageView()).getLayoutY() > controller.getMelonList().get(i).getLimit())
	                ((ImageView) controller.getMelonList().get(i).getImageView()).setLayoutY(((ImageView) controller.getMelonList().get(i).getImageView()).getLayoutY() - speed - ((ImageView) controller.getMelonList().get(i).getImageView()).getLayoutY() / 700 );
	            if(((ImageView) controller.getMelonList().get(i).getImageView()).getLayoutY() <= controller.getMelonList().get(i).getLimit()){
	                ((ImageView) controller.getMelonList().get(i).getImageView()).setLayoutY(((ImageView) controller.getMelonList().get(i).getImageView()).getLayoutY() + speed + ((ImageView) controller.getMelonList().get(i).getImageView()).getLayoutY() / 700);
	                controller.getMelonList().get(i).setLimit(((ImageView) controller.getMelonList().get(i).getImageView()).getLayoutY());   
	            }
	        }
	        
	        for(int i = 0; i < controller.getOrangeList().size(); i++) {
                    
	            if(((ImageView) controller.getOrangeList().get(i).getImageView()).getLayoutY() > controller.getOrangeList().get(i).getLimit())
	                ((ImageView) controller.getOrangeList().get(i).getImageView()).setLayoutY(((ImageView) controller.getOrangeList().get(i).getImageView()).getLayoutY() - speed - ((ImageView) controller.getOrangeList().get(i).getImageView()).getLayoutY() / 700 );
	            if(((ImageView) controller.getOrangeList().get(i).getImageView()).getLayoutY() <= controller.getOrangeList().get(i).getLimit()){
                        {  ((ImageView) controller.getOrangeList().get(i).getImageView()).setLayoutY(((ImageView) controller.getOrangeList().get(i).getImageView()).getLayoutY() + speed + ((ImageView) controller.getOrangeList().get(i).getImageView()).getLayoutY() / 700);
	                       System.out.println("reach limit >>>>>");
                        controller.getOrangeList().get(i).setLimit(((ImageView) controller.getOrangeList().get(i).getImageView()).getLayoutY());   
	           
                        }
                    }
	        }*/
	    }  
	     
}