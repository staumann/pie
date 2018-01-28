package base.gpio.file.implementation;

import java.io.PrintWriter;

import base.gpio.definition.GPIOFileHandler;
import core.config.definition.ConfigManager;
import core.config.implementation.ConfigManagerImpl;

public abstract class AbstractGIOPFileHandler implements GPIOFileHandler {
	private ConfigManager config;
	private PrintWriter fileWriter = null;
	public AbstractGIOPFileHandler() {
		this.config = ConfigManagerImpl.getInstance();
		this.initFile(this.getConfigKey(this.getKey()));
		
	}
	
	public AbstractGIOPFileHandler(String gpio) {
		this.config = ConfigManagerImpl.getInstance();
		String fileName = this.getConfigKey(this.getKey());
		fileName = fileName.replaceAll("\\{gpioid\\}", gpio);
		this.initFile(fileName);
	}
	
	private void initFile(String fileName) {
		try {
			this.fileWriter = new PrintWriter(fileName);
		}catch(Exception e) {
			System.out.println("Error getting File: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	protected PrintWriter getWriter() {
		return this.fileWriter;
	}
	
	protected void writeToFile(String content) {
		fileWriter.write(content);
		fileWriter.flush();
	}
	
	protected String getConfigKey(String key) {
		return this.config.getString(key);
	}
	
	protected ConfigManager getConfig() {
		return this.config;
	}
	
	@Override
	public void addGPIO(String gpio) {
		this.fileWriter.write(gpio);   
        this.fileWriter.flush();
	}
	
	@Override
	public void destroy() {
		this.fileWriter.close();
	}
	
}
