import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerConsole {
	
	public void accept() 
	  {
	    try
	    {
	      BufferedReader fromConsole = 
	        new BufferedReader(new InputStreamReader(System.in));
	      String message;

	      while (true) 
	      {
	        message = fromConsole.readLine();
	        System.out.println(message);
	                
	        	
	        }
	      
	      
	      
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }
	  

}
