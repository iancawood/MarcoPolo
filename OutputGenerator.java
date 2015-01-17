/* CAN MODIFY THIS CLASS 
 * except, do not change the name of the class, or generateOutput
 * 
 */


public class OutputGenerator {
	public static void generateOutput(int numColours, StringBuilder outputStream) {
		outputStream.append("<html>");
		outputStream.append(numColours + " was inputted.");
		outputStream.append("</html>");
	}
}
