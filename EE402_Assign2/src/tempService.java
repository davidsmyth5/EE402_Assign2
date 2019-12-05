import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




public class tempService {

	private static String TEMP_PATH = "/sys/class/thermal/thermal_zone0/temp";
	public tempService()
	   {
		 System.out.println("temp service created");
	   }

	   //method returns date/time as a formatted String object
	   public float getTemperature()
	   {	   
		   String fileName = TEMP_PATH;
	        String line = null;
	        float tempC =0;

	        try {
	            FileReader fileReader = new FileReader(fileName);

	            BufferedReader bufferedReader = new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	                tempC = (Integer.parseInt(line) / 1000);
	                float tempF = ((tempC / 5) * 9) + 32;
	                System.out.println("Temp °C: " + tempC + " Temp °F: " + tempF);
	                
	            }

	            bufferedReader.close();
	            
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + fileName + "'");
	            
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file '" + fileName + "'");
	         }
	        return tempC;
	   }
		  
}
