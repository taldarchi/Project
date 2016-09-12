/**
 * @file Run.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description main method implementation
 * 				
 * @date    08/09/2016
 */
package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.MyController;
import model.MyModel;
import view.MyView;

/**
 * The Class Run.
 */
public class Run {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //standart streams
	    PrintWriter out = new PrintWriter(System.out, true);
	    MyView view=new MyView(in,out);
	    MyModel model=new MyModel();
	    MyController controller=new MyController(view,model);
	    view.setController(controller);
	    model.setController(controller);
	    view.start();

	}

}