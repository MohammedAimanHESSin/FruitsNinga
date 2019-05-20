package factoryPattern;

public class FruitFactory implements Factory {

	public FruitFactory() {
		
	}

	@Override
	public Product getProduct(String fruitType) {
		  if(fruitType == null){
		         return null;
		      }		
		      if(fruitType.equalsIgnoreCase("APPLE")){
		         return new Apple();
		         
		      } else if(fruitType.equalsIgnoreCase("ORANGE")){
		         return new Orange();
		         
		      } else if(fruitType.equalsIgnoreCase("MELLON")){
		         return new Mellon();
		      }
		      return null ;
	}
		  
}
