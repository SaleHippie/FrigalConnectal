import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@SuppressWarnings("serial")
public class parameter extends javax.swing.JFrame {

	/**
	 * Creates new form Porpeties
	 */
	public parameter() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		Header = new javax.swing.JPanel();
		btnHome = new javax.swing.JButton();
		title = new javax.swing.JLabel();
		panelCentre = new javax.swing.JPanel();
		panelTemp = new javax.swing.JPanel();
		tempTitle = new javax.swing.JLabel();
		minTemp = new javax.swing.JLabel();
		spinMinTemp = new javax.swing.JSpinner();
		maxTemp = new javax.swing.JLabel();
		spinMaxTemp = new javax.swing.JSpinner();
		panelHum = new javax.swing.JPanel();
		humTitle = new javax.swing.JLabel();
		minHum = new javax.swing.JLabel();
		spinMinHum = new javax.swing.JSpinner();
		maxHum = new javax.swing.JLabel();
		spinMaxHum = new javax.swing.JSpinner();
		panelContent = new javax.swing.JPanel();
		panelAlert = new javax.swing.JPanel();
		modeTitle = new javax.swing.JLabel();
		modeList = new javax.swing.JComboBox();
		mailTitle = new javax.swing.JLabel();
		mailField = new javax.swing.JTextField();
		localisationTitle = new javax.swing.JLabel();
		localisationField = new javax.swing.JTextField();
		tempUnitTitle = new javax.swing.JLabel();
		tempUnitList = new javax.swing.JComboBox();
		thermostatTitle = new javax.swing.JLabel();
		thermostatSlider = new javax.swing.JSlider();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(512, 400));

		Header.setPreferredSize(new java.awt.Dimension(400, 40));
		Header.setLayout(null);

		btnHome.setText("H");
		btnHome.setPreferredSize(new java.awt.Dimension(40, 40));
		btnHome.setBounds(0, 0, 40, 40);
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Delete this shit temp use for d�mo
				//envoi un mail � yannis
				try {
					sendMail();
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Header.add(btnHome);

		title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		title.setText("Param�tre");
		title.setBounds(180, 10, 120, 20);
		Header.add(title);

		getContentPane().add(Header, java.awt.BorderLayout.PAGE_START);

		panelCentre.setPreferredSize(new java.awt.Dimension(190, 300));
		panelCentre.setLayout(new javax.swing.BoxLayout(panelCentre, javax.swing.BoxLayout.Y_AXIS));

		panelTemp.setPreferredSize(new java.awt.Dimension(390, 50));
		panelTemp.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		tempTitle.setText("Tem�rature : ");
		panelTemp.add(tempTitle);

		minTemp.setText("min ");
		panelTemp.add(minTemp);

		spinMinTemp.setPreferredSize(new java.awt.Dimension(60, 30));
		panelTemp.add(spinMinTemp);

		maxTemp.setText("max ");
		panelTemp.add(maxTemp);

		spinMaxTemp.setPreferredSize(new java.awt.Dimension(60, 30));
		panelTemp.add(spinMaxTemp);

		panelCentre.add(panelTemp);

		panelHum.setPreferredSize(new java.awt.Dimension(390, 50));
		panelHum.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		humTitle.setText("Humidit� : ");
		panelHum.add(humTitle);

		minHum.setText("min ");
		panelHum.add(minHum);

		spinMinHum.setPreferredSize(new java.awt.Dimension(60, 30));
		panelHum.add(spinMinHum);

		maxHum.setText("max ");
		panelHum.add(maxHum);

		spinMaxHum.setPreferredSize(new java.awt.Dimension(60, 30));
		panelHum.add(spinMaxHum);

		panelCentre.add(panelHum);

		panelContent.setPreferredSize(new java.awt.Dimension(390, 220));
		panelContent.setLayout(new java.awt.GridBagLayout());

		panelAlert.setPreferredSize(new java.awt.Dimension(390, 200));
		panelAlert.setLayout(new java.awt.GridLayout(5, 2, -100, 2));

		modeTitle.setText("mode d'alerte");
		panelAlert.add(modeTitle);

		modeList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mail", "Pop-up" }));
		panelAlert.add(modeList);

		mailTitle.setText("Adresse mail :");
		panelAlert.add(mailTitle);
		panelAlert.add(mailField);

		localisationTitle.setText("localisation");
		panelAlert.add(localisationTitle);

		localisationField.setText("783058");
		localisationField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});
		panelAlert.add(localisationField);

		tempUnitTitle.setText("unit� de tem�rature");
		panelAlert.add(tempUnitTitle);

		tempUnitList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Celsius", "Fahrenheit" }));
		panelAlert.add(tempUnitList);

		thermostatTitle.setText("th�rmostat");
		panelAlert.add(thermostatTitle);

		thermostatSlider.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
		thermostatSlider.setMajorTickSpacing(2);
		thermostatSlider.setMaximum(10);
		thermostatSlider.setMinorTickSpacing(1);
		thermostatSlider.setPaintLabels(true);
		thermostatSlider.setPaintTicks(true);
		thermostatSlider.setSnapToTicks(true);
		thermostatSlider.setValue(5);
		thermostatSlider.setDoubleBuffered(true);
		thermostatSlider.setExtent(2);
		thermostatSlider.setMaximumSize(new java.awt.Dimension(200, 40));
		thermostatSlider.setPreferredSize(new java.awt.Dimension(200, 30));
		thermostatSlider.setValueIsAdjusting(true);
		panelAlert.add(thermostatSlider);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
		panelContent.add(panelAlert, gridBagConstraints);

		panelCentre.add(panelContent);

		getContentPane().add(panelCentre, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>                        

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
		// TODO add your handling code here:
	}                                           
	
	
	/**
	 * envoie un mail � l'utilisateur
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	private void sendMail() throws AddressException, MessagingException{
	    String to = "yayouzer@gmail.com";
	    final String username = "frigo.connecte@gmail.com";
	    final String password = ".Etml-44";
	 
	    //Set les propri�t�s de la connection SMTP
	    Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	 
		// connection
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication( username, password );
					}
				});
	    
	    //set le mail
	    Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("from@no-spam.com"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
		message.setSubject("Testing Subject");
		message.setText("Salut mec," +
				"\n\n Check ton frigo �a chauffe !");
		//envoie le mail
		Transport.send(message);
	}
	
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(parameter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(parameter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(parameter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(parameter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new parameter().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify                     
	private javax.swing.JPanel panelCentre;
	private javax.swing.JPanel Header;
	private javax.swing.JButton btnHome;
	private javax.swing.JComboBox modeList;
	private javax.swing.JComboBox tempUnitList;
	private javax.swing.JLabel tempTitle;
	private javax.swing.JLabel title;
	private javax.swing.JLabel minHum;
	private javax.swing.JLabel maxHum;
	private javax.swing.JLabel minTemp;
	private javax.swing.JLabel humTitle;
	private javax.swing.JLabel maxTemp;
	private javax.swing.JLabel mailTitle;
	private javax.swing.JLabel modeTitle;
	private javax.swing.JLabel tempUnitTitle;
	private javax.swing.JLabel localisationTitle;
	private javax.swing.JLabel thermostatTitle;
	private javax.swing.JPanel panelTemp;
	private javax.swing.JPanel panelHum;
	private javax.swing.JPanel panelAlert;
	private javax.swing.JPanel panelContent;
	private javax.swing.JSlider thermostatSlider;
	private javax.swing.JSpinner spinMinHum;
	private javax.swing.JSpinner spinMaxHum;
	private javax.swing.JSpinner spinMinTemp;
	private javax.swing.JSpinner spinMaxTemp;
	private javax.swing.JTextField localisationField;
	private javax.swing.JTextField mailField;
	// End of variables declaration                   
}