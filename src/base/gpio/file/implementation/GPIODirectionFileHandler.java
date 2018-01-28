package base.gpio.file.implementation;


import base.gpio.definition.GPIOModes;

public class GPIODirectionFileHandler extends AbstractGIOPFileHandler {
	
	public GPIODirectionFileHandler(String gpio) {
		super(gpio);
	}
	
	public void setGPIODirection(GPIOModes mode) {
		this.writeToFile(mode.toString().toLowerCase());
		this.destroy();
	}
	@Override
	public String getKey() {
		return "directionfile";
	}

}
