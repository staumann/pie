package core.config.implementation;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import core.config.definition.ConfigManager;

public class ConfigParser {

	private final String configPath = "config.xml";
	
	public void readConfig(ConfigManager manager) {
		InputStream configFile = ConfigParser.class.getResourceAsStream(configPath);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			if(configFile == null) {
				System.out.println("Fuck the Config is null");
			}
			SAXParser saxParser = factory.newSAXParser();
			
			saxParser.parse(configFile, ConfigHandler.getHandler(ConfigManagerImpl.getInstance()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
