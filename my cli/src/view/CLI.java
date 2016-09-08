package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.Command;

public class CLI{

	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String,Command> map;
	boolean exit=false;
	
	
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> map) {
		super();
		this.in = in;
		this.out = out;
		this.map = map;
	}
	
	public void start(){
	    out.println("Please Choose a Command:\n\n"
	    		+ "dir <path> - Display files/folders in the desired path\n"
	    		+ "generate_3d_maze <name> <X,Y,Z> <algorithm(simple/growing_tree_random/growing_tree_last> - Create a maze with desired parameters\n"
	    		+ "display <name> - Print maze\n"
	    		+ "display_cross_section <name> <X/Y/Z> <index> - Display 2d array of the desired index in a maze\n"
	    		+ "save_maze <name> <file name> - Save maze\n"
	    		+ "load_maze <file name> <name> - Load maze\n"
	    		+ "solve <name> <algorithm> - Solve desired maze with desired algorithm\n"
	    		+ "display_solution <name> - Display solution\n"
	    		+ "exit - Close and exit everything\n");
		new Thread(new Runnable(){
			@Override
			public void run() { 
				try {
				String line;
				while(!(line=in.readLine()).equalsIgnoreCase("exit")){
					try{
					Scanner s = new Scanner(line);
					String string = new String();
					String command=s.next();
					if(map.containsKey(command))
						switch (command){
						case "dir":
							Command dir=map.get("dir");
							boolean bool=true;
							while(s.hasNext()){
								if(bool){
									string+=s.next();
									bool=false;
								}
								else
									string+=" "+s.next();
							}
							dir.doCommand(string);
							s.close();
							break;
						case "generate_3d_maze":
							Command generate=map.get("generate_3d_maze");
							while(s.hasNext())
								string+=s.next()+ " ";
							generate.doCommand(string);
							s.close();
							break;
						case "display":
							Command display=map.get("display");
							while(s.hasNext())
								string+=s.next();
							display.doCommand(string);
							s.close();
							break;
						case "display_cross_section":
							Command display_cross_section=map.get("display_cross_section");
							while(s.hasNext())
								string+=s.next()+ " ";
							display_cross_section.doCommand(string);
							s.close();
							break;
						case "save_maze":
							Command save_maze=map.get("save_maze");
							while(s.hasNext())
								string+=s.next()+ " ";
							save_maze.doCommand(string);
							s.close();
							break;
						case "load_maze":
							Command load_maze=map.get("load_maze");
							while(s.hasNext())
								string+=s.next()+ " ";
							load_maze.doCommand(string);
							s.close();
							break;
						case "solve":
							Command solve=map.get("solve");
							while(s.hasNext())
								string+=s.next()+ " ";
							solve.doCommand(string);
							s.close();
							break;
						case "display_solution":
							Command display_solution=map.get("display_solution");
							while(s.hasNext())
								string+=s.next();
							display_solution.doCommand(string);
							s.close();
							break;
						default:
							out.println("No such command");
							break;
						}
					else
						out.println("No such command");
				}catch(NoSuchElementException e){}
				
				}
				Command exit=map.get("exit");
				exit.doCommand(null);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		    }
		    }).start();
	}

}
