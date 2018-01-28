package base;

import java.util.stream.Stream;

public class CMDHelper {

    private static CMDHelper INSTANCE;
    private boolean debug = false;

    public void getArgs(String[] args) {
        Stream.of(args).filter(a -> a.contains("=")).forEach(a -> {
            String[] parts = a.split("=");

            if("debug".equals(parts[0])) {
                this.debug = Boolean.parseBoolean(parts[1]);
            }
        });
    }

    public boolean getDebug() {
        return this.debug;
    }

    public static CMDHelper getINSTANCE() {
        if(CMDHelper.INSTANCE == null) {
            CMDHelper.INSTANCE = new CMDHelper();
        }

        return CMDHelper.INSTANCE;
    }
}
