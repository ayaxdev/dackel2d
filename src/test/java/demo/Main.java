package demo;

public class Main {

    private static DemoApplication application;

    public static void main(String[] args) {
        if ((application = new DemoApplication()).run())
            System.exit(0);
        else
            System.exit(1);
    }

    public static DemoApplication getDemo() {
        return application;
    }

}