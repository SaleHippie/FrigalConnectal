import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

@SuppressWarnings("serial")
public class Main extends JPanel{
	//private static JPanel panelWarning = new JPanel();
	private JButton btnStat = new JButton("Données/Statistiques");
	private JButton btnParam = new JButton("Paramètres");
	private JButton moduleMeteo = new JButton();

	private static JPanel panelInfo = new JPanel();
	private JLabel alerte = new JLabel();
	private JLabel info = new JLabel();
	private String infoRSS = "Test Info : Ceci est un communiqué !";
	public static JFrame window = new JFrame("Frigo connecté");
	//private float mX;

	public static void main( String[] arg ) throws Exception
	{

		window.setContentPane(new Main());;
		window.pack();
		window.setResizable(false);
		window.setVisible( true );
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


	}

	protected void render() {
		//Graphics g = getGraphics();
		/*	if (g != null) {
			Dimension d = getSize();

			Graphics imageGraphics = getGraphics();
			// Clear the image background.
			imageGraphics.setColor(getBackground());
			imageGraphics.fillRect(0, 0, d.width, d.height);
			imageGraphics.setColor(getForeground());*/
		// Draw this component offscreen.
		paint();
		/*// Clean up.
			imageGraphics.dispose();
			g.dispose();
		}*/
	}

	Main() throws Exception{
		infoRSS = getRSSString();

		panelInfo.setPreferredSize(new Dimension( 512,400 ) );
		//mX = panelInfo.getPreferredSize().width;

		panelInfo.add(alerte);
		//implémentation des boutons
		btnStat.setPreferredSize(new Dimension( 200, 200 ));
		btnParam.setPreferredSize(new Dimension( 200, 200 ));
		moduleMeteo.setPreferredSize(new Dimension(407, 100));
		btnStat.setVisible(true);
		btnParam.setVisible(true);
		info.setText(infoRSS);
		// interface coponnent
		panelInfo.add(btnStat);
		panelInfo.add(btnParam);
		panelInfo.add(moduleMeteo);
		panelInfo.add(info);
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//mX = mX - 1;
				if( infoRSS.length() == 0 ){
					try {
						infoRSS = getRSSString();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//mX = panelInfo.getPreferredSize().width;
				}
				render();
			}
		};

		Timer  t = new Timer(200, taskPerformer);
		t.start();

		//add(panelWarning, BorderLayout.PAGE_START);
		add(panelInfo, BorderLayout.PAGE_END);
	}

	public void paint() {
		// Draw the string. | Abandon suite à des problème de design pattern
		/*g = panelInfo.getGraphics(); 
		g.setFont(getFont());
		g.drawString( infoRSS, (int) mX, panelInfo.getHeight() - 10);
		g.dispose();*/
		infoRSS = infoRSS.substring(1);
		info.setText(infoRSS);
	}

	private String getRSSString() throws Exception{
		String rssFlux = "";
		Hashtable<String, String> link = new Hashtable<String, String>();

		link.put("Actualités : ", "http://www.20min.ch/rss/rss.tmpl?type=channel&get=17&lang=ro");
		link.put("Suisse :  ", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=313&lang=ro");
		link.put("Monde :  ", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=312&lang=ro");
		link.put("Economie :  ", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=316&lang=ro");
		link.put("Faits divers :  ", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=318&lang=ro");
		link.put("Vaud :  ", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=315&lang=ro");
		link.put("Geneve :  ", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=314&lang=ro");
		link.put("Romandie :  ", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=784&lang=ro");
		link.put("People :  ", "http://www.20min.ch/rss/rss.tmpl?type=channel&get=22&lang=ro");
		link.put("Sport :  ", "http://www.20min.ch/rss/rss.tmpl?type=channel&get=23&lang=ro");
		link.put("Hi-tech :  ", "http://www.20min.ch/rss/rss.tmpl?type=channel&get=20&lang=ro");
		link.put("Sortir :  ", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=526&lang=ro");

		Enumeration<String> e = link.keys();
		while( e.hasMoreElements() ){
			String key = (String) e.nextElement();
			rssFlux +=  key;
			rssFlux += getRssFromURL( link.get(key) );
		}

		return rssFlux;
	}

	public static String getRssFromURL( String urlGet ) throws Exception{


		URL url = new URL( urlGet );
		URLConnection conn = url.openConnection();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(conn.getInputStream());

		//Pour parcourir le document et l'afficher
		return affichage(document.getFirstChild(), 0);
	}

	public static String affichage(Node N, int profondeur) {
		String ret = "";
		Node fils;
		Node petitFils;
		if (N != null) {
			if (N.hasChildNodes()) {
				fils = N.getFirstChild();
				fils = fils.getNextSibling();
				fils = fils.getFirstChild();

				while( fils != null ){

					if( fils.hasChildNodes())
					{
						petitFils = fils.getFirstChild();

						while( petitFils != null){

							if( petitFils.getNodeName() == "title" ){
								ret += petitFils.getTextContent() + ". ";	
							}
							//if(petitFils.getNodeName() == "link" ){
							//	ret += petitFils.getTextContent() + " ";	
							//}

							petitFils = petitFils.getNextSibling();
						}
					}
					fils = fils.getNextSibling();
				}
			}
		}
		return ret;
	}
}
