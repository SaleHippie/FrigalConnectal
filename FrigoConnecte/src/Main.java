import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JPanel{
	private static JPanel panelWarning = new JPanel();
	private static JPanel panelInfo = new JPanel();
	private JLabel info = new JLabel("Test 1");
	private String infoRSS = "Test Info : Ceci est un communiqué !";
	private float mX;
	private static Image image = null;

	public static void main( String[] arg )
	{
		JFrame window = new JFrame("Frigo connecté");

		window.setContentPane(new Main());;
		window.pack();
		window.setVisible( true );
	}

	protected void render() {
		Graphics g = getGraphics();
		if (g != null) {
			Dimension d = getSize();

			Graphics imageGraphics = getGraphics();
			// Clear the image background.
			imageGraphics.setColor(getBackground());
			imageGraphics.fillRect(0, 0, d.width, d.height);
			imageGraphics.setColor(getForeground());
			// Draw this component offscreen.
			paint(imageGraphics);
			// Clean up.
			imageGraphics.dispose();
			g.dispose();
		}
	}



	Main(){
		panelInfo.setPreferredSize(new Dimension( 512,400 ) );
		mX = panelInfo.getPreferredSize().width;

		panelInfo.add(info);
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mX = mX - 1;
				render();
			}
		};

		Timer  t = new Timer(80, taskPerformer);
		t.start();

		add(panelInfo);
	}

	public void paint(Graphics g) {
		Graphics g2 = (Graphics) g;

		// Draw the string.
		g2.setFont(getFont());
		g2.drawString( infoRSS, (int) mX, panelInfo.getHeight() - 10);
	}
}
