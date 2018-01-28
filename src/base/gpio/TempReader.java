package base.gpio;

import base.base.gipio.ro.TempRatureResult;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;


public class TempReader {
	private static final int MAXTIMINGS = 85;
	private final int[] dht11_dat = { 0, 0, 0, 0, 0 };
	private float humidity  = 0.0f;
	private float tempratureC = 0.0f;
	private float tempratureF = 0.0f;

	public TempReader() {

		// setup wiringPi
		if (Gpio.wiringPiSetup() == -1) {
			System.out.println(" ==>> GPIO SETUP FAILED");
			return;
		}

		GpioUtil.export(3, GpioUtil.DIRECTION_OUT);
	}

	/**
	 * This Method gets the current Temperatur an Huminity
	 *
	 * @param pin
	 *
	 * @return
	 */
	public boolean getTemperature(final int pin) {
		int laststate = Gpio.HIGH;
		int j = 0;
		dht11_dat[0] = dht11_dat[1] = dht11_dat[2] = dht11_dat[3] = dht11_dat[4] = 0;

		Gpio.pinMode(pin, Gpio.OUTPUT);
		Gpio.digitalWrite(pin, Gpio.LOW);
		Gpio.delay(18);

		Gpio.digitalWrite(pin, Gpio.HIGH);
		Gpio.pinMode(pin, Gpio.INPUT);

		for (int i = 0; i < MAXTIMINGS; i++) {
			int counter = 0;
			while (Gpio.digitalRead(pin) == laststate) {
				counter++;
				Gpio.delayMicroseconds(1);
				if (counter == 255) {
					break;
				}
			}

			laststate = Gpio.digitalRead(pin);

			if (counter == 255) {
				break;
			}

			/* ignore first 3 transitions */
			if (i >= 4 && i % 2 == 0) {
				/* shove each bit into the storage bytes */
				dht11_dat[j / 8] <<= 1;
				if (counter > 16) {
					dht11_dat[j / 8] |= 1;
				}
				j++;
			}
		}
		// check we read 40 bits (8bit x 5 ) + verify checksum in the last
		// byte
		if (j >= 40 && checkParity()) {
			float h = (float) ((dht11_dat[0] << 8) + dht11_dat[1]) / 10;
			if (h > 100) {
				h = dht11_dat[0]; // for DHT11
			}
			float c = (float) (((dht11_dat[2] & 0x7F) << 8) + dht11_dat[3]) / 10;
			if (c > 125) {
				c = dht11_dat[2]; // for DHT11
			}
			if ((dht11_dat[2] & 0x80) != 0) {
				c = -c;
			}
			final float f = c * 1.8f + 32;

			this.humidity = h;
			this.tempratureC = c;
			this.tempratureF = f;

			return true;
		}else {
			return false;
		}

	}

	public void getTemperature(final int pin, TempRatureResult result) {
		if(this.getTemperature(pin)) {
			result.setHuminity(this.getHumidity());
			result.setTemprature(this.getTempratureC());
		}
	}

	private boolean checkParity() {
		return dht11_dat[4] == (dht11_dat[0] + dht11_dat[1] + dht11_dat[2] + dht11_dat[3] & 0xFF);
	}

	public float getHumidity() {
		return this.humidity;
	}

	public float getTempratureC() {
		return this.tempratureC;
	}

	public float getTempratureF() {
		return this.tempratureF;
	}

	
}
