import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jfree.chart.ChartPanel;
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
	private String info = getRSSString();
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

		/*panelInfo.setPreferredSize(new Dimension( 512,400 ) );
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




		//add(panelWarning, BorderLayout.PAGE_START);
		add(panelInfo, BorderLayout.PAGE_END);*/
		GridBagConstraints gridBagConstraints;

		jScrollPane1 = new javax.swing.JScrollPane();
		jEditorPane1 = new javax.swing.JEditorPane();
		panelGeneral = new javax.swing.JPanel();
		infoAlert = new javax.swing.JLabel();
		infoRSS = new javax.swing.JLabel();
		panelModuleCentral = new javax.swing.JPanel();
		btnData = new javax.swing.JButton();
		btnSetting = new javax.swing.JButton();
		panelModuleMeteo = new javax.swing.JPanel();
		panelMeteo = new javax.swing.JPanel();
		temperature = new javax.swing.JLabel();
		windForce = new javax.swing.JLabel();
		sunset = new javax.swing.JLabel();
		humidity = new javax.swing.JLabel();
		windDirection = new javax.swing.JLabel();
		sunrise = new javax.swing.JLabel();
		conditions = new javax.swing.JLabel();
		location = new javax.swing.JLabel();
		meteoBackground = new javax.swing.JLabel();
		applicationBackground = new javax.swing.JLabel();

		test = new JPanel();

		jScrollPane1.setViewportView(jEditorPane1);

		setPreferredSize(new java.awt.Dimension(512, 400));

		panelGeneral.setBackground(new Color(0,0,0,1));
		panelGeneral.setLayout(new java.awt.BorderLayout());

		infoAlert.setForeground(new java.awt.Color(255, 0, 0));
		infoAlert.setText("Alert 1 : votre frigo indique la temperature de 20° :s");
		panelGeneral.add(infoAlert, java.awt.BorderLayout.NORTH);
		infoRSS.setPreferredSize(new Dimension( 20,40 ));
		panelGeneral.add( infoRSS, java.awt.BorderLayout.SOUTH );

		panelModuleCentral.setBackground(new Color(0,0,0,1));
		panelModuleCentral.setLayout(new java.awt.GridBagLayout());

		btnData.setIcon(new javax.swing.ImageIcon("D:\\DATA\\jolyma\\tools\\images\\Definitif\\BtnDataV2.png")); // NOI18N
		btnData.setPreferredSize(new java.awt.Dimension(200, 200));
		panelModuleCentral.add(btnData, new java.awt.GridBagConstraints());

		btnSetting.setIcon(new javax.swing.ImageIcon("D:\\DATA\\jolyma\\tools\\images\\Definitif\\BtnToolsV3.png")); // NOI18N
		btnSetting.setPreferredSize(new java.awt.Dimension(200, 200));
		panelModuleCentral.add(btnSetting, new java.awt.GridBagConstraints());

		panelModuleMeteo.setLayout(new BorderLayout());

		panelMeteo.setBackground(new Color(0,0,0,1));
		panelMeteo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
		panelMeteo.setLayout(new java.awt.GridBagLayout());

		// initialisation des variables de la météo
		setWeather();

		temperature.setFont(new java.awt.Font("Meiryo UI", 1, 36)); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 4);
		panelMeteo.add(temperature, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
		panelMeteo.add(windForce, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		panelMeteo.add(sunset, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 4);
		panelMeteo.add(humidity, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
		panelMeteo.add(windDirection, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		panelMeteo.add(sunrise, gridBagConstraints);


		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
		panelMeteo.add(conditions, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 7);
		panelMeteo.add(location, gridBagConstraints);
		panelMeteo.setBounds(0, 0, 400, 100);
		panelModuleMeteo.add(panelMeteo);
		// Défini le background de la météo pour avoir un petit soleil ou un vieux nuage selon la météo
		meteoBackground.setIcon(new javax.swing.ImageIcon(getBackgroundWeather(conditions.getText()))); // NOI18N
		meteoBackground.setBounds(0, 0, 400, 0);
		panelModuleMeteo.add(meteoBackground, BorderLayout.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		panelModuleCentral.add(panelModuleMeteo, gridBagConstraints);

		panelGeneral.add(panelModuleCentral, java.awt.BorderLayout.CENTER);

		add(panelGeneral);
		panelGeneral.setBounds(10, 10, 480, 340);

		applicationBackground.setIcon(new javax.swing.ImageIcon("D:\\DATA\\jolyma\\tools\\images\\WallpaperV3.png")); // NOI18N
		window.getContentPane().add(applicationBackground);
		applicationBackground.setBounds(0, 0, 500, 370);

		// lance le flux RSS
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//mX = mX - 1;
				if( info.length() == 0 ){
					try {
						info = getRSSString();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//mX = panelInfo.getPreferredSize().width;
				}
				paint();
			}
		};

		Timer  t = new Timer(500, taskPerformer);
		t.start();


	}

	private String getBackgroundWeather(String weather) {

		String path = "";

		switch (weather) {
		case "snow": case "snow flurries":case "light snow showers":case "blowing snow":case "sleet":case "cold":
			case "scattered snow showers":case "heavy snow":case"snow showers":
			path = "D:\\DATA\\jolyma\\tools\\images\\Météo\\OK\\MeteoNeigeV1.png";
			break;

		case "cloudy": case "mostly cloudy": case"partly cloudy":case "windy":
			path = "D:\\DATA\\jolyma\\tools\\images\\Météo\\OK\\MeteoNuageV2.png";
			break;

		case "showers":case "freezing rain":case "blustery":case "mixed rain and hail":case "scattered showers":case "hail":
			path = "D:\\DATA\\jolyma\\tools\\images\\Météo\\OK\\MeteoPluieV1.png";
			break;

		case "foggy":case "freezing drizzle": case "drizzle": case "dust": case "haze":case "smoky":
			path = "D:\\DATA\\jolyma\\tools\\images\\Météo\\OK\\MeteoFogV1.png";
			break;

		case "isolated thunderstorms":case "scattered thunderstorms": case "thundershowers":
			path = "D:\\DATA\\jolyma\\tools\\images\\Météo\\OK\\MeteoOrageV1.png";
			break;

		default:
			path = "D:\\DATA\\jolyma\\tools\\images\\Météo\\OK\\MeteoV2.png";
			break;
		}
		return path;
	}

	public void paint() {
		// Draw the string. | Abandon suite à des problème de design pattern
		/*g = panelInfo.getGraphics(); 
		g.setFont(getFont());
		g.drawString( infoRSS, (int) mX, panelInfo.getHeight() - 10);
		g.dispose();*/
		info = info.substring(1);
		infoRSS.setText(info);

		super.updateUI();

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
	private void setWeather(){
		// 783058 = code de Lausanne / c = celsius
		// Instancie la classe WeatherDoc se qui initialise les valeurs
		//Supress Warnigs cause the librery is build like that
		@SuppressWarnings("unused")
		//TODO Rechercher la valeur dans la bd pour la localisation.  783058
		WeatherDoc doc = new WeatherDoc("783058", "c");
		//on créer maintenant un objet dérivé de WeatherDoc pour récupérer les données
		WeatherDisplay disp = new WeatherDisplay();

		//Affichages dans la consoles des valeurs qui peuvent être utiles
		temperature.setText( disp.getTemperature() + "°" + disp.getTemperatureUnit() );
		humidity.setText("Humidité : " + disp.getHumidity() + "%");
		location.setText(disp.getCity() + ", " + disp.getCountry());
		conditions.setText( disp.getCondition() );
		sunset.setText("Coucher : " + disp.getSunset());
		sunrise.setText("Lever : " + disp.getSunrise());
		String direction = disp.getWindDirection();
		windForce.setText("Vent : " +  disp.getWindSpeed() + "m/s" );
		windDirection.setText("Direction : " + getDirection( Integer.parseInt(direction) ) );
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

	// Variables declaration - do not modify                     
	private javax.swing.JLabel windDirection;
	private javax.swing.JLabel applicationBackground;
	private javax.swing.JButton btnData;
	private javax.swing.JButton btnSetting;
	private javax.swing.JLabel conditions;
	private javax.swing.JLabel humidity;
	private javax.swing.JLabel infoAlert;
	private javax.swing.JLabel infoRSS;
	private javax.swing.JEditorPane jEditorPane1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel location;
	private javax.swing.JLabel meteoBackground;
	private javax.swing.JPanel panelGeneral;
	private javax.swing.JPanel panelMeteo;
	private javax.swing.JPanel panelModuleCentral;
	private javax.swing.JPanel panelModuleMeteo;
	private javax.swing.JLabel sunrise;
	private javax.swing.JLabel sunset;
	private javax.swing.JLabel temperature;
	private javax.swing.JLabel windForce;

	private JPanel test;
	// End of variables declaration        

}
