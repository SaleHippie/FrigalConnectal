import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.teknikindustries.yahooweather.WeatherDisplay;
import com.teknikindustries.yahooweather.WeatherDoc;


@SuppressWarnings("serial")
public class Main extends JPanel{
	private String info = getRSSString();
	boolean firstTimeInstanciated = false;
	parameter param = new parameter();
	Data data = new Data();
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	int stopSpam = 0;
	public static JFrame window = new JFrame("Frigo connecté");
	//private float mX;

	public static void main( String[] arg ) throws Exception
	{
		window.setContentPane(new JLabel(new ImageIcon("D:\\DATA\\jolyma\\tools\\images\\WallpaperV3.png")));
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

	Main() throws Exception, ClassNotFoundException{
		panelGeneral = new JPanel();

		setPreferredSize(new java.awt.Dimension(512, 400));

		panelGeneral.setBackground(new Color(0,0,0,1));
		panelGeneral.setLayout(new java.awt.BorderLayout());

		constructMenu();
	}

	private Image getBackgroundWeather(String weather) throws IOException {

		Image backgroundWeather = null;
		InputStream input;


		switch (weather.toLowerCase()) {
		case "snow": case "snow flurries":case "light snow showers":case "blowing snow":case "sleet":case "cold":
		case "scattered snow showers":case "heavy snow":case"snow showers":

			input = classLoader.getResourceAsStream("MeteoNeigeV1.png");
			backgroundWeather = ImageIO.read(input);
			break;

		case "cloudy": case "mostly cloudy": case"partly cloudy":case "windy":case "mist":

			input = classLoader.getResourceAsStream("MeteoNuageV2.png");
			backgroundWeather = ImageIO.read(input);
			break;

		case "showers":case "freezing rain":case "blustery":case "mixed rain and hail":case "scattered showers":case "hail":

			input = classLoader.getResourceAsStream("MeteoPluieV1.png");
			backgroundWeather = ImageIO.read(input);
			break;

		case "foggy":case "freezing drizzle": case "drizzle": case "dust": case "haze":case "smoky":

			input = classLoader.getResourceAsStream("MeteoFogV1.png");
			backgroundWeather = ImageIO.read(input);
			break;

		case "isolated thunderstorms":case "scattered thunderstorms": case "thundershowers":

			input = classLoader.getResourceAsStream("MeteoOrageV1.png");
			backgroundWeather = ImageIO.read(input);
			break;

		default:

			input = classLoader.getResourceAsStream("MeteoV2.png");
			backgroundWeather = ImageIO.read(input);
			break;
		}
		return backgroundWeather;
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


	private void openSettings(ActionEvent evt) {

		panelGeneral.removeAll();
		param.setBounds(0, 0, 400, 512);
		panelGeneral.add(param);
		window.pack();
		param.setVisible(true);

	}


	private void openData(ActionEvent evt) throws ClassNotFoundException, IOException, SQLException {

		panelGeneral.removeAll();

		data.setBounds(0, 0, 400, 512);
		panelGeneral.add(data);
		window.pack();
		data.setVisible(true);
	}


	public void closeSettings() throws IOException, ClassNotFoundException, SQLException{
		//TODO fermé settings et ouvrir menu
		param.pushParameters();
		panelGeneral.removeAll();
		constructMenu();
		infoAlert.setText("Alert : aucune");
	}

	public void closeData() throws IOException{
		//TODO fermé settings et ouvrir menu
		panelGeneral.removeAll();
		constructMenu();
	}

	private void constructMenu() throws IOException {
		GridBagConstraints gridBagConstraints;


		infoAlert = new JLabel();
		infoRSS = new JLabel();
		panelModuleCentral = new JPanel();
		btnData = new JButton();
		btnSetting = new JButton();
		panelModuleMeteo = new JPanel();
		panelMeteo = new JPanel();
		temperature = new JLabel();
		windForce = new JLabel();
		sunset = new JLabel();
		humidity = new JLabel();
		windDirection = new JLabel();
		sunrise = new JLabel();
		conditions = new JLabel();
		location = new JLabel();
		meteoBackground = new JLabel();
		applicationBackground = new JLabel();
		InputStream input;

		infoAlert.setForeground(new java.awt.Color(255, 0, 0));
		
		panelGeneral.add(infoAlert, java.awt.BorderLayout.NORTH);
		infoRSS.setPreferredSize(new Dimension( 20,40 ));
		panelGeneral.add( infoRSS, java.awt.BorderLayout.SOUTH );

		panelModuleCentral.setBackground(new Color(0,0,0,1));
		panelModuleCentral.setLayout(new java.awt.GridBagLayout());

		//TODO Rename variables + do the same for all pictures

		input = classLoader.getResourceAsStream("BtnDataV2.png");
		Image logoData = ImageIO.read(input);


		btnData.setIcon(new ImageIcon(logoData));
		btnData.setPreferredSize(new java.awt.Dimension(200, 200));
		btnData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					openData(evt);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelModuleCentral.add(btnData, new java.awt.GridBagConstraints());

		input = classLoader.getResourceAsStream("BtnToolsV3.png");
		Image logoParam = ImageIO.read(input);

		btnSetting.setIcon(new ImageIcon(logoParam)); // NOI18N
		btnSetting.setPreferredSize(new java.awt.Dimension(200, 200));
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				openSettings(evt);
			}
		});
		panelModuleCentral.add(btnSetting, new java.awt.GridBagConstraints());

		panelModuleMeteo.setLayout(new BorderLayout());

		panelMeteo.setBackground(new Color(0,0,0,1));
		panelMeteo.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
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
		meteoBackground.setIcon(new ImageIcon(getBackgroundWeather(conditions.getText()))); // NOI18N
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

		applicationBackground.setIcon(new ImageIcon("D:\\DATA\\jolyma\\tools\\images\\WallpaperV3.png")); // NOI18N 
		//panelGeneral.add(applicationBackground);
		applicationBackground.setBounds(0, 0, 512, 400);

		// évite d'instancier plusieurs fois des actions et gangner en performance
		if( firstTimeInstanciated == false){

			// lance le flux RSS et les alertes
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//flux RSS
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
					//End flux RSS

					//Alerte

					double temp = data.lastTemp;
					double hum = data.lastHum;
					
					String alert = param.getAlert(temp, hum);

					if( alert != ""){
						alert = "Alerte : Votre frigo est : " + alert  + "\n Donnée actuel : " + temp + "°" + getTempUnit().toUpperCase() + " " + hum + "%";
						if( stopSpam == 0){

							infoAlert.setText(alert);
							stopSpam = 20;
							switch (param.modeList.getSelectedIndex()) {
							case 0:
								try {
									param.sendMail(alert);
								} catch (AddressException e) {
									JOptionPane.showMessageDialog(panelGeneral ,"mail non valide",
											"Alerte",JOptionPane.ERROR_MESSAGE);
								} catch (MessagingException e) {

								}
								break;

							case 1:
								JOptionPane.showMessageDialog(panelGeneral ,alert,
										"Alerte",JOptionPane.ERROR_MESSAGE);
								break;

							default:
								break;
							}
						}
						stopSpam--;
					}else{
						infoAlert.setText("Alert : aucune");
					}
				}
			};

			param.btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						closeSettings();
					} catch (IOException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			data.btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						closeData();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			Timer  t = new Timer(500, taskPerformer);
			t.start();

			firstTimeInstanciated = true;
		}
	}

	/**
	 * Pour le moment récupert des données météorologique et les impriment dans la console
	 */
	private void setWeather(){
		// 783058 = code de Lausanne / c = celsius
		// Instancie la classe WeatherDoc se qui initialise les valeurs
		//Supress Warnigs cause the librery is build like that

		//TODO Rechercher la valeur dans la bd pour la localisation.  783058
		String unitTemp = getTempUnit();


		@SuppressWarnings("unused")
		WeatherDoc doc = new WeatherDoc(param.localisationField.getText(), unitTemp);
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

	private String getTempUnit() {
		String unitTemp = "c";

		switch (param.tempUnitList.getSelectedIndex()) {
		case 0:
			unitTemp = "c";
			break;

		case 1:
			unitTemp = "f";
			break;

		default:
			break;
		}

		return unitTemp;
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
	private JLabel windDirection;
	private JLabel applicationBackground;
	private JButton btnData;
	private JButton btnSetting;
	private JLabel conditions;
	private JLabel humidity;
	private JLabel infoAlert;
	private JLabel infoRSS;
	private JLabel location;
	private JLabel meteoBackground;
	private JPanel panelGeneral;
	private JPanel panelMeteo;
	private JPanel panelModuleCentral;
	private JPanel panelModuleMeteo;
	private JLabel sunrise;
	private JLabel sunset;
	private JLabel temperature;
	private JLabel windForce;
	// End of variables declaration        

}
