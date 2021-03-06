package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

/**
 * The Class DisplayMazeWindow.
 */
public class DisplayMazeWindow extends DialogWindow{
	
	/** The view. */
	private MyView view;
	
	/**
	 * Instantiates a new display maze window.
	 *
	 * @param view the view
	 */
	public DisplayMazeWindow(MyView view) {
		this.view=view;
	}
	
	/* (non-Javadoc)
	 * @see view.DialogWindow#initWidgets()
	 */
	@Override
	protected void initWidgets() {
		shell.setText("Display maze window");
		shell.setSize(350, 100);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Maze name: ");
		
		
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				
		Button btnDisplayMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnDisplayMaze);
		btnDisplayMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnDisplayMaze.setText("Display maze");
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Generating...");
				//msg.setMessage("Button was clicked");
				String name=txtName.getText();		
				msg.setMessage("Displaying maze "+name);
				msg.open();
				String s="display "+name;
				view.executeCommand(s);
				
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
