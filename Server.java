/* DO NO MODIFY */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException{
	    @SuppressWarnings("resource")
		ServerSocket listener = new ServerSocket(1337);
	    while(true){
	      Socket sock = listener.accept();	      
	      
	      PrintWriter out =
	        new PrintWriter(sock.getOutputStream(), true);
	      
	      BufferedReader in =
	        new BufferedReader(
	            new InputStreamReader(sock.getInputStream()));
	    
	      boolean post = false;
	      int numberOfMarbles = 0;
	      
	      String input;
	      while(in.ready() && (input = in.readLine()) != null)
	      {
	    	  if(input.contains("numberOfMarbles"))
	    	  {
	    		  post = true;
	    		  String [] items = input.split(" ");
	    		  String [] parmsWhole = items[1].split("\\?");
	    		  String [] params = parmsWhole[1].split("\\&");
	    		  
	    		  for(String param : params)
	    		  {
	    			  String name = param.split("=")[0];
	    			  String value = param.split("=")[1];
	    			  if(name.equals("numberOfMarbles"))
	    			  {
	    				  numberOfMarbles = Double.valueOf(value).intValue();
	    			  }
	    		  }
	    		  
	    		  break;
	    	  }
	      }
	      
	      if(post)
	      {	    	  
	    	  StringBuilder output = new StringBuilder();
	    	  OutputGenerator.generateOutput(numberOfMarbles, output);
	    	  out.println(output.toString());	    	  
	      }
	      else
	      {
	    	  BufferedReader br = new BufferedReader(new FileReader("src/index.html"));
	    	  String line;
	    	  while ((line = br.readLine()) != null) {
	    	     out.println(line);
	    	  }
	    	  br.close();
	      }
	    
	      sock.close();
	    }
	  }
}