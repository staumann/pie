package base.gpio.file.implementation;

public class GPIOExportFileHandler extends AbstractGIOPFileHandler {
	public static GPIOExportFileHandler INSTANCE = null;
	public static GPIOExportFileHandler getInstance() {
		if(GPIOExportFileHandler.INSTANCE == null) {
			GPIOExportFileHandler.INSTANCE = new GPIOExportFileHandler();
		}
		
		return GPIOExportFileHandler.INSTANCE;
	}

	@Override
	public String getKey() {
		return "exportfilename";
	}

}
