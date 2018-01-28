package core.config.implementation;

import java.util.HashMap;
import java.util.Map;

import base.CMDHelper;
import core.config.definition.ConfigManager;

public class ConfigManagerImpl implements ConfigManager {
	private static ConfigManager INSTANCE = null;
	private Map<String, String> stringConfig;
	private Map<String, Integer> intConfig;
	private Map<String, Boolean> boolConfig;
	
	private boolean configFound;
	public ConfigManagerImpl() {
		this.stringConfig = new HashMap<>();
		this.boolConfig = new HashMap<>();
		this.intConfig = new HashMap<>();
		
		this.configFound = false;
	}
	
	public static ConfigManager getInstance() {
		if(ConfigManagerImpl.INSTANCE == null) {
			ConfigManagerImpl.INSTANCE = new ConfigManagerImpl();
			ConfigParser parser = new ConfigParser();
			parser.readConfig(ConfigManagerImpl.getInstance());
		}
		
		return ConfigManagerImpl.INSTANCE;
	}
	@Override
	public String getString(String key) {
		String returnValue = this.stringConfig.get(key);
		if(returnValue == null) {
			System.out.println("Error getting setting for key :" + key);
		}
		return returnValue;
	}
	@Override
	public int getInteger(String key) {
		return this.intConfig.get(key);
	}
	@Override
	public boolean getBoolean(String key) {
		return this.boolConfig.get(key);
	}
	@Override
	public void setConfigFound(boolean found) {
		this.configFound = found;
		
	}
	@Override
	public void setConfigValue(String key, String value) {
		if(!configFound) {
			System.out.println("WARNING: setConfigValue called but no Config was found");
		} 
		this.stringConfig.put(key, value);
	}
	
	@Override
	public void setConfigValue(String key, int value) {
		if(!configFound) {
			System.out.println("WARNING: setConfigValue called but no Config was found");
		} 
		this.intConfig.put(key, value);
	}
	
	@Override
	public void setConfigValue(String key, boolean value) {
		if(!configFound) {
			System.out.println("WARNING: setConfigValue called but no Config was found");
		} 
		this.boolConfig.put(key, value);
	}
}
