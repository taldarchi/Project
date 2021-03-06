package presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import algorithms.mazeGenerators.Position;
import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

/**
 * The Class Presenter.
 */
public class Presenter implements Observer{
	
	/** The view. */
	private View view;
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new presenter.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Presenter(MyView view, MyModel model){
	      this.view=view;
	      this.model=model;
	      model.loadSolutionMap();
	      HashMap<String,Command> map=new HashMap<String,Command>();
		  map.put("dir", new Command_dir(view));
		  map.put("generate_3d_maze", new Command_generate_3d_maze(view, model));
		  map.put("display", new Command_display(view, model));
		  map.put("display_cross_section", new Command_display_cross_section(view, model));
		  map.put("save_maze", new Command_save_maze(view, model));
		  map.put("load_maze", new Command_load_maze(view, model));
		  map.put("solve", new Command_solve(view, model));
		  map.put("up", new Command_up(view, model));
		  map.put("down", new Command_down(view, model));
		  map.put("forward", new Command_forward(view, model));
		  map.put("character_move", new Command_character_move(view,model));
		  map.put("load_properties", new Command_read_properties(view,model));
		  map.put("backwards", new Command_backwards(view, model));
		  map.put("left", new Command_left(view, model));
		  map.put("right", new Command_right(view, model));
		  map.put("hint", new Command_hint(view, model));
		  map.put("exit",new Command_exit(view));
		  view.setMap(map);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o==view){
			if(arg.equals("save_solution_map")){
				model.saveSolutionMap();
				return;
			}
			Command command;
			boolean bool=true;
			String line=new String((String)arg);
			Scanner s = new Scanner(line);
			String string = new String();
			command=((View) view).getMap().get(s.next());
			while(s.hasNext()){
				if(bool){
					string+=s.next();
					bool=false;
				}
				else
					string+=" "+s.next();
			}
			try {
				command.doCommand(string);
			} catch (IOException e) {
				e.printStackTrace();
			}
			s.close();
		}
		if(o==model){
			if(arg instanceof Position)
				view.setHintPositionInGui((Position)arg);
			else if(arg.equals("character_move")){
				Command c=new Command_character_move(view, model);
				try {
					c.doCommand("character_move");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				String str=(String)arg;
				((View) view).printMessage(str);
			}
		}
	}
}
