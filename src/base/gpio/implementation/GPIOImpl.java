package base.gpio.implementation;

import java.io.File;

import base.gpio.definition.GPIO;
import base.gpio.definition.GPIOModes;
import base.gpio.file.implementation.GPIOCommandFileHandler;
import base.gpio.file.implementation.GPIODirectionFileHandler;
import base.gpio.file.implementation.GPIOExportFileHandler;
import base.gpio.file.implementation.GPIOUnexportFileHandler;
import core.config.definition.ConfigManager;
import core.config.implementation.ConfigManagerImpl;

public class GPIOImpl implements GPIO {

	private String gpioID;
	private GPIOExportFileHandler exportFileHandler;
	private GPIOUnexportFileHandler unexportFileHandler;
	private GPIOCommandFileHandler commandFileHandler;
	private ConfigManager config;
	
	public GPIOImpl(String gpioID) {
		this.gpioID = gpioID;
		exportFileHandler = GPIOExportFileHandler.getInstance();
		unexportFileHandler = GPIOUnexportFileHandler.getInstance();
		this.config = ConfigManagerImpl.getInstance();
		exportFileHandler.addGPIO(gpioID);
        /*** Init GPIO port for output ***/
        // Open file handles to GPIO port unexport and export controls
    	
        // Reset the port
        File exportFileCheck = new File(this.config.getString("pinfolder")+gpioID);
        if (exportFileCheck.exists()) {
            this.unexportFileHandler.addGPIO(gpioID);
        }
        
        this.commandFileHandler = new GPIOCommandFileHandler(gpioID);
		
	}

	public GPIOImpl(int gpioID) {
		this("" + gpioID);
	}

	@Override
	public void setMode(GPIOModes mode) {
		GPIODirectionFileHandler directionFileHandler =  new GPIODirectionFileHandler(gpioID);
		directionFileHandler.setGPIODirection(mode);
	}
	
	
	public void setValue(String value) {
		this.commandFileHandler.setValue(value);
	}
	
	public void clear() {
		this.exportFileHandler.destroy();
		this.commandFileHandler.destroy();
		this.unexportFileHandler.destroy();
	}

	@Override
	protected void finalize() {
		this.setValue("0");
	}
}
