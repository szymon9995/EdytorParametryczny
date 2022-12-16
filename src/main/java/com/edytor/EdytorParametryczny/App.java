package com.edytor.EdytorParametryczny;

import javax.swing.SwingUtilities;

public class App 
{
    public static void main( String[] args )
    {
    	
    	SwingUtilities.invokeLater(new Runnable()
    			{

					@Override
					public void run() {
						MainWindow window = new MainWindow();
						window.Show();
						
					};
    	
    			});
    			
    	//Test2 test = new Test2();
    	//test.Run();
    }
    
 
}
