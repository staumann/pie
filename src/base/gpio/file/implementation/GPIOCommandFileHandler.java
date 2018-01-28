package base.gpio.file.implementation;

public class GPIOCommandFileHandler extends AbstractGIOPFileHandler {
	
	public GPIOCommandFileHandler(String gpio) {
		super(gpio);
	}
	
	public void setValue(String value) {
		this.writeToFile(value);
	}
	@Override
	public String getKey() {
		return "commandfile";
	}

}
