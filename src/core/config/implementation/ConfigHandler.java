package core.config.implementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.CMDHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import core.config.definition.ConfigManager;

public class ConfigHandler {
	
	private static final String SETTING = "setting";
	private static final String GLOBAL = "global";
	private static final String CONFIG = "config";
	private static final String KEY = "key";
	private static final String VALUE = "value";
	
	private static final Pattern decimalPattern = Pattern.compile("\\d*");

	public static DefaultHandler getHandler(ConfigManager manager) {
		CMDHelper helper = CMDHelper.getINSTANCE();
		return new DefaultHandler() {
			@Override
			public void endDocument() throws SAXException {
				
				super.endDocument();
			}
			
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {
				super.startElement(uri, localName, qName, attributes);
				
				switch(qName) {
					case SETTING:
						this.handleSettings(attributes);
						break;
					case CONFIG:
						this.handleConfig();
						break;
					case GLOBAL:
					default:
						break;
				}

			}
			
			private void handleSettings(Attributes attributes) {
				String key = attributes.getValue(KEY);
				String value = attributes.getValue(VALUE);
				
				if("true".equals(value) || "false".equals(value)) {
					manager.setConfigValue(key, Boolean.valueOf(value));
				}else {
					Matcher matcher = decimalPattern.matcher(value);
					
					if(matcher.matches()) {
						manager.setConfigValue(key, Integer.valueOf(value));
					}else {
						manager.setConfigValue(key, value);	
					}
				}
				if(helper.getDebug()) {
					System.out.println("New ConfigValue found for key: " + key + " with Value: " + value);
				}
				
			}
			private void handleConfig() {
				manager.setConfigFound(true);
			}
		};
	}

}
