import java.util.List;
import java.util.ArrayList;


public class temperature {
private float temp;
private List<Float> temperatures = new ArrayList<>();
private List<Float> avgTemperatures = new ArrayList<>();
private final int LISTSIZE =20;

private Graph myGraph= new Graph(temperatures);

	public temperature(){
		//temperatures= new ArrayList<>();
        //Random random = new Random();
        //int maxDataPoints = 20;
        //int maxScore = 10;
        //for (int i = 0; i < maxDataPoints; i++) {
        	//temperatures.add(((float) 1));
        //}
	}
	public temperature(float temp){
	
	}
	public void setTemp(float temp){
		this.temp=temp;
	}
	
	public List<Float> addAvgTemp(float temp){
		if (avgTemperatures.size()>LISTSIZE){
			avgTemperatures.remove(0);
		}
		avgTemperatures.add(temp);
		System.out.println("Average Temperature: " +temp);
		
		myGraph.setAvgTemperatures(avgTemperatures);
		return avgTemperatures;
	}
	
	public List<Float> addTemp(float temp){
		if (temperatures.size()>LISTSIZE){
			temperatures.remove(0);
		}
		temperatures.add(temp);
		
		System.out.println("Current Temperature Reading: " +temp);
		System.out.println(temperatures.size());
		addAvgTemp(calculateAverage(temperatures));
		myGraph.setTemperatures(temperatures);
		return temperatures;
	}
	private float calculateAverage(List <Float> temps) {
		  float sum = 0;
		  if(!temps.isEmpty()) {
		    for (Float temp : temps) {
		        sum += temp;
		    }
		    return sum / temps.size();
		  }
		  
		  return sum;
		}
	public List<Float> getTemperatures(){
		return temperatures;
	}
	
	public float getTemp(){
		Client myClient = new Client(Client.IPADDRESS);
		temp=myClient.getTemp();
		addTemp(temp);
		return temp;
	}
	
}
