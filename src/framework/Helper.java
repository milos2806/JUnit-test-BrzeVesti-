package framework;

public class Helper {

    public static String getRandomText() {

//        int random = (int) (Math.random() * 250 + 1);
//        String randomText = "mv" + random;
//        return randomText;
        return "mv" + (int) (Math.random() * 500 + 1);
    }
    
    public static String getRandomTextReg() {
        return "mvReg" + (int) (Math.random() * 200 + 1);
    }

    public static String getRandomTextPor() {
        return "mvPor" + (int) (Math.random() * 250 + 1);
    }

    public static String getRandomUrl() {
        return "http://test.rs";
    }
}
