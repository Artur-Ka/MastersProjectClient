package ua.onclinic;

import java.awt.EventQueue;

import ua.onclinic.gui.MainFrame;
import ua.onclinic.model.InstancesManager;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 15 квіт. 2017 р.
 */
public class Client
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
//		            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//		            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				}
				catch (Throwable thrown)
				{
					thrown.printStackTrace();
				}
				
				ServerThread.getInstance();
				InstancesManager.getInstance();
				MainFrame.getInstance().setVisible(true);
			}
		});
	}
}