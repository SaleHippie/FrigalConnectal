import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.teknikindustries.yahooweather.WeatherDisplay;
import com.teknikindustries.yahooweather.WeatherDoc;


@SuppressWarnings("serial")
public class Main extends JPanel{
	private ChartPanel panelStat;
	private static JButton btnHome = new JButton();
	private JButton btnStat = new JButton("Données/Statistiques");
	private JButton btnParam = new JButton("Paramètres");
	private JButton moduleMeteo = new JButton("Espace météo !!!");

	private JComboBox<String> lstStat = new JComboBox<String>();

	private static JPanel panelInfo = new JPanel();
	private JPanel panelParam = new JPanel();
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
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

	/*protected void render() {
		//Graphics g = getGraphics();
		/*	if (g != null) {
			Dimension d = getSize();

			Graphics imageGraphics = getGraphics();
			// Clear the image background.
			imageGraphics.setColor(getBackground());
			imageGraphics.fillRect(0, 0, d.width, d.height);
			imageGraphics.setColor(getForeground());
		// Draw this component offscreen.
		paint();
		/// Clean up.
			imageGraphics.dispose();
			g.dispose();
		}
	}*/

	Main() throws Exception{
	//	infoRSS = getRSSString();
		
		panelInfo.setPreferredSize(new Dimension( 512,400 ) );
		//mX = panelInfo.getPreferredSize().width
		panelInfo.add(alerte);

		//implémentation des boutons
		btnStat.setPreferredSize(new Dimension( 200, 200 ));
		btnParam.setPreferredSize(new Dimension( 200, 200 ));
		moduleMeteo.setPreferredSize(new Dimension(407, 100));

		btnStat.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO désinstencié panelInfo
				
				
			//	chart.setPadding(new RectangleInsets(60, 100, 100, 100));
				//panelStat = new ChartPanel(chart);
				panelStat.setLayout(null);
				panelInfo.setVisible(false);
				
				//Le design c'est de la grosse merde
				btnHome.setIcon(new ImageIcon("C:\\Users\\jolyma\\Desktop\\home.png"));
				btnHome.setBounds(80, 5, 40, 40);
				panelStat.add(btnHome);
				btnHome.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						panelInfo.setVisible(true);
						panelStat.setVisible(false);
					}
				});
				
				
				panelStat.add( new JLabel("Stat : "));
				lstStat.addItem("Données en temps réel");
				lstStat.addItem("Graphique température");
				lstStat.setBounds(256, 5, 180, 30);
				panelStat.add(lstStat);

				panelStat.setVisible(true);
				add(panelStat);
			}
		});

		btnStat.setVisible(true);
		btnParam.setVisible(true);
		info.setText(infoRSS);

		// interface componnent
		panelInfo.add(btnStat);
		panelInfo.add(btnParam);
		panelInfo.add(moduleMeteo, BorderLayout.CENTER);
		weather();
		panelInfo.add(info, BorderLayout.PAGE_END);
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
				paint();
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

	/**
	 * Isole le text compris dans la balise de Titre du fichier XML
	 * @param N la première balise
	 * @param profondeur int Hiérarchie
	 * @return String La valeur comprise dans la balise "title"
	 */
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
	

	/**
	 * Pour le moment récupert des données météorologique et les impriment dans la console
	 */
	private void weather(){
		// 783058 = code de Lausanne / c = celsius
		// Instancie la classe WeatherDoc se qui initialise les valeurs
		//Supress Warnigs cause the librery is build like that
		@SuppressWarnings("unused")
		WeatherDoc doc = new WeatherDoc("783058", "c");
		//on créer maintenant un objet dérivé de WeatherDoc pour récupérer les données
		WeatherDisplay disp = new WeatherDisplay();

		//TODO implémenter la météo dans l'interface
		//Affichages dans la consoles des valeurs qui peuvent être utiles
		System.out.println( "Temp : " + disp.getTemperature() + "°" + disp.getTemperatureUnit() );
		System.out.println("Humidity : " + disp.getHumidity() + "%");
		System.out.println(disp.getCity() + " " + disp.getCondition());
		System.out.println("lever : " + disp.getSunrise());
		System.out.println("coucher : " + disp.getSunset());
		String direction = disp.getWindDirection();
		System.out.println( disp.getWindSpeed() + "m/s" + "|" + (Double.parseDouble(disp.getWindSpeed())*3.6) + "Km/h " + getDirection( Integer.parseInt(direction)  ) + direction );
		System.out.println("pression : " + disp.getPressure() + disp.getPressureUnit());
		System.out.println(disp.getCountry() );
	}

	/**
	 * Converti une donnée (azimuth) en direction
	 * @param azimuth int compri entre 0 et 360.
	 * @return String direction en short name (N, SW, etc...)
	 */
	private String getDirection(int azimuth){

		//parse the azimuth of compass for the common Users
		if (azimuth <= 22) {
			return "N";
		}
		else if( azimuth <= 67){
			return "NE";
		}
		else if( azimuth <= 112){
			return "E";
		}
		else if( azimuth <= 157){
			return "SE";
		}
		else if( azimuth <= 202){
			return "S";
		}
		else if( azimuth <= 247){
			return "SW";
		}
		else if( azimuth <= 292){
			return "W";
		}
		else if( azimuth <= 337){
			return "NW";
		}
		else{ //case between 338 and 360
			return "N";
		}

	}
}
