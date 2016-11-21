import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class DownloadItem {

	public SwingWorker sw;
	public JProgressBar progressBar = new JProgressBar();
	public DownloadItem(int num, AddJson ad, Scanner scanner) {
		// TODO Auto-generated constructor stub
		progressBar.setMaximum(300);
		JFrame jf = new JFrame();
		
		jf.add(progressBar);
		jf.setSize(500,80);
		jf.setVisible(true);
		sw = new SwingWorker<Void,Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				
				// TODO Auto-generated method stub
				for (int i = 1; i<100; i++){
					String s = scanner.nextLine();
					ad.addVideo(i);
					setProgress(i);
					ad.addBook(s);
					setProgress(i);
					//System.out.println("before");
					ad.addMusic(s);
					//System.out.println("after");
					setProgress(i);
				}
				
				
				
				return null;
			}
			
			protected void done() {
				jf.dispose();
			}
			
		};
		sw.addPropertyChangeListener(new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				if("progress".equals(arg0.getPropertyName())){
			          if(SwingUtilities.isEventDispatchThread())
			            System.out.println("Dans le listener donc dans l'EDT ! ");
			          //On r�cup�re sa nouvelle valeur
			          progressBar.setValue(progressBar.getValue()+1);
			    }   
			}         
		});
		sw.execute();
	}
	
	
}