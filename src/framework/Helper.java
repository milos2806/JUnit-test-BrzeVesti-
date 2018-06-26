package framework;

public class Helper {

    public static String getRandomText() {

//        int random = (int) (Math.random() * 250 + 1);
//        String randomText = "mv" + random;
//        return randomText;
        return "mv" + (int) (Math.random() * 500 + 1);
    }

    public static int  getRandomIntiger() {
        return (int) (Math.random() * 1000);
    }
    
    public static int  getRandomIntiger(int range) {
        return (int) (Math.random() * range);
    }

    public static String getRandomUrl() {
        return "http://test.rs";
    }
}
