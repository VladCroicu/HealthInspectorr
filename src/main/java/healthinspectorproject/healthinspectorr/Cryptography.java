package healthinspectorproject.healthinspectorr;

public class Cryptography {

    public static String encriptCypher(String password, int offset){
        String s = "";
        int len = password.length();
        for(int x = 0; x < len; x++){
            s += (char)(password.charAt(x) + offset);
        }
        return s;
    }

    public static String decriptCypher(String password, int offset){
        String s = "";
        int len = password.length();
        for(int x = 0; x < len; x++){
            s += (char)(password.charAt(x) - offset);
        }
        return s;
    }
}

