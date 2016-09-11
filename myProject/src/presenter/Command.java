/**
 * @file Command.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description an interface for the commands in the MVC
 * 				
 * @date    08/09/2016
 */
package presenter;

import java.io.IOException;

/**
 * The Interface Command.
 */
public interface Command {
	
	/**
	 * Do command.
	 *
	 * @param string the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void doCommand(String string) throws IOException;
}
