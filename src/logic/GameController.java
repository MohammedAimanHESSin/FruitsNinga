package logic;

import java.util.ArrayList;

import CommandPattern.*;
import factoryPattern.*;

public class GameController implements GameActions {
	RemoteContoller contoller ;
	Receiver receiver ;
	Command saveCommand ;
	Command loadCommand;
	Factory factory1 ;
	Factory factory2 ;
    ArrayList<GameObject> orangeList;
    ArrayList<GameObject> melonList;

	private GameController() {
		this.contoller = new RemoteContoller();
        this.receiver= new File();
		this.loadCommand=new LoadCommand(receiver);
		this.saveCommand=new SaveCommand(receiver);
		this.factory1= new FruitFactory();
		this.factory2=new BombFactory();
		this.melonList= new ArrayList<GameObject>();
		this.orangeList= new ArrayList<GameObject>();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void ResetGame() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateObjectsLocations() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void saveGame() {
    contoller.setCommand(saveCommand);	
    contoller.pressButton();
	}

	@Override
	public void loadGame() {
	 contoller.setCommand(loadCommand);	
	 contoller.pressButton();
	}


	/** Singletone !! **/
	 private static GameController instance1 = null;
	 public synchronized static GameController getInstance() 
	 {
		 
	        if (instance1 == null) {
	            instance1 = new GameController();
	        }
	        return instance1;
	  }



	@Override
	public GameObject createGameObject(String ObjectType,String factoryType) {
		
		if (factoryType==null) 		return (GameObject) ((BombFactory)factory2).getProduct(ObjectType);

		if(factoryType.equalsIgnoreCase("fruit"))
		
		return (GameObject) ((FruitFactory)factory1).getProduct(ObjectType);
		
		 return null;
    }


	public ArrayList<GameObject> getOrangeList() {
		return orangeList;
	}


	public void setOrangeList(ArrayList<GameObject> orangeList) {
		this.orangeList = orangeList;
	}


	public ArrayList<GameObject> getMelonList() {
		return melonList;
	}


	public void setMelonList(ArrayList<GameObject> melonList) {
		this.melonList = melonList;
	}

    @Override
    public void sliceObjects(GameObject gameObject) {
      // if( gameObject.isSliced() )
           gameObject.slice();
       
    }
	

}
