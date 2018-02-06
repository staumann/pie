package base;

import base.base.gipio.ro.TempRatureResult;
import base.gpio.TempReader;
import com.pi4j.io.gpio.*;
import core.config.definition.ConfigManager;
import core.config.implementation.ConfigManagerImpl;
import core.io.definition.OutPutHandler;
import core.io.implementation.DefaultOutputHandlerImpl;
import core.wrapper.CommonContainer;

public class Application {

    private ConfigManager config;
    private final GpioController gpio;

    {
        config = ConfigManagerImpl.getInstance();
        gpio = GpioFactory.getInstance();
    }
    public void startApp() {
        OutPutHandler handler = new DefaultOutputHandlerImpl();
        TempRatureResult resultRo = new TempRatureResult();
        CommonContainer<GpioPinDigitalOutput> container = new CommonContainer<>();
        container.set(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.HIGH));
        handler.printLine("Getting Data From Sensor");
        Thread dataGatherer = new Thread(() -> {
            TempReader reader = new TempReader();
            int counter = 0;

            while(!resultRo.getAvailable()) {
                if(counter == config.getInteger("switchtoredled")) {
                    container.get().setState(PinState.LOW);
                    container.set(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.HIGH));
                }
                container.get().setState(PinState.HIGH);
                reader.getTemperature(config.getInteger("pin"), resultRo);
                try {
                    java.lang.Thread.sleep(1000);
                    container.get().setState(PinState.LOW);
                    java.lang.Thread.sleep(1000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }
        });
        dataGatherer.start();
        int counter = 0;
        int modificator = 1;
        while(!resultRo.getAvailable()) {
            try {
                if(modificator > 0) {
                    handler.printLine(".");
                }else {
                    handler.removeLetterFromConsole();
                }

                if(counter == 2) {
                    modificator = -1;
                }
                if(counter < 0) {
                    modificator = 1;
                }

                counter = counter + modificator;
                java.lang.Thread.sleep(500);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }

        }
        handler.newLine();
        handler.newLine();
        handler.printLine("Humidity = " + resultRo.getHuminity() + " Temperature = " + resultRo.getTemprature());
        handler.newLine();

        container.get().setState(PinState.LOW);
        gpio.shutdown();
    }
}
