import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day9 {
	
	/* Works both for part 1 and 2. Just need to change the variable that is returned from score to garbageRemoved. */
	public static int calculateRemovedGarbage() throws IOException {
		String fileName = "/Users/sertaysener/eclipse-workspace/Day9/src/input.txt";
		FileReader inputStream = null;
		int groups = 0;
		int depth = 0;
		int score = 0;
		int garbageRemoved = 0;
		boolean garbage = false;
		boolean cancel = false;
		
		try {
			inputStream = new FileReader(fileName);
			int c;
            while ((c = inputStream.read()) != -1) {
               char character = (char) c;
               if (!cancel) {
	            	   	if(garbage && character != '!' && character != '>') {
	       	   			garbageRemoved++;
	       	   		}
            	   		if(character == '!') {
            	   			cancel = true;
            	   		}
            	   		else if(character == '<') {
            	   			if(!garbage) {
            	   				garbage = true;
            	   			}
            	   		}
            	   		else if(character == '>') {
            	   			if(garbage) {
            	   				garbage = false;
            	   			}
            	   		}
            	   		else if(character == '{') {
            	   			if(!garbage) {
            	   				depth++;
            	   			}
		        	   	}
		            else if(character == '}') {
			            	if(!garbage) {
			            		score += depth;
			            		depth--;
			            		groups++;
			            	}
		            }
               }
               else {
            	   	cancel = false;
               }
            }
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {                
	            e.printStackTrace();
		}
		finally {
			if (inputStream != null) {
                inputStream.close();
            }
		}
		
		return garbageRemoved;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.print(calculateRemovedGarbage());
	}
}
