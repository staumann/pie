package base;

import base.base.gipio.ro.TempRatureResult;
import base.gpio.TempReader;
import base.gpio.definition.GPIO;
import base.gpio.definition.GPIOModes;
import base.gpio.implementation.GPIOImpl;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;
import core.config.definition.ConfigManager;
import core.config.implementation.ConfigManagerImpl;
import core.io.definition.OutPutHandler;
import core.io.implementation.DefaultOutputHandlerImpl;
import core.wrapper.CommonContainer;

import java.util.function.Supplier;

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
        CommonContainer<GPIO> container = new CommonContainer<>();
        container.set(new GPIOImpl(RaspiPin.GPIO_17.getAddress()));
        handler.printLine("Getting Data From Sensor");
        Thread dataGatherer = new Thread(() -> {
            TempReader reader = new TempReader();
            container.get().setMode(GPIOModes.OUT);
            int counter = 0;

            while(!resultRo.getAvailable()) {
                if(counter == config.getInteger("switchtoredled")) {
                    container.get().setValue("0");
                    container.set(new GPIOImpl(RaspiPin.GPIO_27.getAddress()));
                    container.get().setMode(GPIOModes.OUT);
                }
                container.get().setValue("1");
                reader.getTemperature(config.getInteger("pin"), resultRo);
                try {
                    java.lang.Thread.sleep(1000);
                    container.get().setValue("0");
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
        container.get().setValue("0");
        handler.newLine();
        handler.newLine();
        handler.printLine("Humidity = " + resultRo.getHuminity() + " Temperature = " + resultRo.getTemprature());
        handler.newLine();
        handler.newLine();
    }
}
