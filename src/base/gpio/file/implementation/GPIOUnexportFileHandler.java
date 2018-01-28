package base.gpio.file.implementation;

public class GPIOUnexportFileHandler extends AbstractGIOPFileHandler {
	public static GPIOUnexportFileHandler INSTANCE = null;
	public static GPIOUnexportFileHandler getInstance() {
		if(GPIOUnexportFileHandler.INSTANCE == null) {
			GPIOUnexportFileHandler.INSTANCE = new GPIOUnexportFileHandler();
		}
		
		return GPIOUnexportFileHandler.INSTANCE;
	}

	@Override
	public String getKey() {
		return "unexportfilename";
	}

}
