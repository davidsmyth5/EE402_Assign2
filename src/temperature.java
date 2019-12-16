import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class temperature {
private float temp;
private List<Float> temperatures = new ArrayList<>();;
private final int LISTSIZE =20;
private Client myClient = new Client();

	public temperature(){
		//temperatures= new ArrayList<>();
        //Random random = new Random();
        //int maxDataPoints = 20;
        //int maxScore = 10;
        //for (int i = 0; i < maxDataPoints; i++) {
        	//temperatures.add(((float) i));
       // }
	}
	public temperature(float temp){
	
	}
	public void setTemp(float temp){
		this.temp=temp;
	}
	public List<Float> addTemp(float temp){
		if (temperatures.size()>LISTSIZE){
			temperatures.remove(0);
		}
		temperatures.add(temp);
		return temperatures;
	}
	
	public List<Float> getTemperatures(){
		return temperatures;
	}
	
	public float getTemp(){
		temp=myClient.getTemp();
		return temp;
	}
	
}
