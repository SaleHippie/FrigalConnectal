package main;

import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
//Les 5 import suivants si on veut exporter en XML...



public class main {
	public static void main( String[] arg ) throws Exception
	{	
		Hashtable<String, String> link = new Hashtable<String, String>();
		
		link.put("Actualités : \n", "http://www.20min.ch/rss/rss.tmpl?type=channel&get=17&lang=ro");
		link.put("Suisse : \n", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=313&lang=ro");
		link.put("Monde : \n", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=312&lang=ro");
		link.put("Economie : \n", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=316&lang=ro");
		link.put("Faits divers : \n", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=318&lang=ro");
		link.put("Vaud : \n", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=315&lang=ro");
		link.put("Geneve : \n", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=314&lang=ro");
		link.put("Romandie : \n", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=784&lang=ro");
		link.put("People : \n", "http://www.20min.ch/rss/rss.tmpl?type=channel&get=22&lang=ro");
		link.put("Sport : \n", "http://www.20min.ch/rss/rss.tmpl?type=channel&get=23&lang=ro");
		link.put("Hi-tech : \n", "http://www.20min.ch/rss/rss.tmpl?type=channel&get=20&lang=ro");
		link.put("Sortir : \n", "http://www.20min.ch/rss/rss.tmpl?type=rubrik&get=526&lang=ro");

		Enumeration e = link.keys();
		while( e.hasMoreElements() ){
			String key = (String) e.nextElement();
			System.out.println( key );
			printRssFromURL( link.get(key) );
		}
		
		
	}

	public static void printRssFromURL( String urlGet ) throws Exception{


		URL url = new URL( urlGet );
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
