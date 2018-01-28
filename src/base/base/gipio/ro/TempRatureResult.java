package base.base.gipio.ro;

public class TempRatureResult {
    private float temprature;
    private float huminity;
    private boolean available;

    public synchronized float getTemprature() {
        return temprature;
    }

    public void setTemprature(float temprature) {
        this.temprature = temprature;
        this.available = true;
    }

    public synchronized float getHuminity() {
        return huminity;
    }

    public void setHuminity(float huminity) {
        this.huminity = huminity;
        this.available = true;
    }

    public synchronized boolean getAvailable() {
        return this.available;
    }
}
