package base.gpio.definition;

public interface GPIOFileHandler {
	void destroy();
	String getKey();
	void addGPIO(String gpio);
}
