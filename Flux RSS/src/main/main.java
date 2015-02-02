package main;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
//Les 5 import suivants si on veut exporter en XML...



public class main {
	public static void main( String[] arg ) throws Exception
	{	
		System.out.println("Actualités : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=channel&get=17&lang=ro");
		System.out.println("Suisse : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=313&lang=ro");
		System.out.println("Monde : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=312&lang=ro");
		System.out.println("Economie : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=316&lang=ro");
		System.out.println("Faits divers : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=318&lang=ro");
		System.out.println("Vaud : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=315&lang=ro");
		System.out.println("Geneve : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=314&lang=ro");
		System.out.println("Romandie : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=784&lang=ro");
		System.out.println("People : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=channel&get=22&lang=ro");
		System.out.println("Sport : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=channel&get=23&lang=ro");
		System.out.println("Hi-tech : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=channel&get=20&lang=ro");
		System.out.println("Sortir : \n");
		printRssFromURL("http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=526&lang=ro");
		

		//Pour transformer en XML, ça permet de voir si le document est bien construit
		//TransformerFactory tf = TransformerFactory.newInstance();
		//Transformer t = tf.newTransformer();
		//t.transform(new DOMSource(document),new StreamResult(new File("toto.xml")));


	}

	public static void printRssFromURL( String urlGet ) throws Exception{

		//File fichier = new File("Toto.xml");
		URL url = new URL( urlGet ); //("http://fobec.com/CMS/fobec.xml");
		URLConnection conn = url.openConnection();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(conn.getInputStream());

		//Pour parcourir le document et l'afficher
		System.out.println(affichage(document.getFirstChild(), 0));
	}

	public static Node ajoutNoeud(Node parent, String valeur1, String valeur2)
	{
		Element element = parent.getOwnerDocument().createElement("Noeud");
		element.setAttribute("code",valeur1);
		element.setAttribute("texte",valeur2);
		parent.appendChild(element);
		return element;
	}

	/**
	 * Parcours par récurrence le noeud N, pour afficher ou faire tout autre
	 * traitement sur l'arborescence
	 * @param Node N le noeud à afficher
	 * @param int profondeur pour connaître la profondeur où on est
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
					/*if( fils.getNodeName() == "title" ){
						ret += fils.getTextContent() + "\n";
					}*/

					if( fils.hasChildNodes())
					{
						petitFils = fils.getFirstChild();

						while( petitFils != null){
							
							if( petitFils.getNodeName() == "title" ){
								ret += petitFils.getTextContent() + "\t";	
							}
							if(petitFils.getNodeName() == "link" ){
								ret += petitFils.getTextContent() + "\n";	
							}
							
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
