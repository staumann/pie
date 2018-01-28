package base;

import core.config.implementation.ConfigManagerImpl;

import java.util.stream.Stream;

public class StartUp {

    /**
     * We don't want an instance of the class
     */
    private StartUp() {

    }


	public static void main(final String args[]) {

		if(args.length > 0) {
		    CMDHelper.getINSTANCE().getArgs(args);
            ConfigManagerImpl.getInstance();
        }
		Application app = new Application();
        app.startApp();

		System.exit(0);

	}

}
