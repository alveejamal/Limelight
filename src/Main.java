import com.company.Encoder;
import com.company.EncoderImpl;


public class Main {

    public static void main(String[] args) {
        // this map will be used to ge the position of the input string in the keyboard

        Encoder encoder = new EncoderImpl();


        String transformation = "-41";
        String input_text = "Shorab Jamal";

        String[][] encodedKeyboard= encoder.keyboardEncoder(transformation);
        String ans = encoder.encodeString(input_text, encodedKeyboard);

        System.out.println(input_text);
        System.out.println(ans);


        transformation = "H, V, H, 15, H, V, -12,V,H,V,V,H,H,H,-100,58,H";
        input_text = "Lorem ipsum dolor sit er elit lamet, consectetaur cillium adipisicing pecu, sed do eiusmod" +
                "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud" +
                "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in" +
                "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint" +
                "occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Nam liber te conscient to factor tum poen legum odioque civiuda.";

        encodedKeyboard= encoder.keyboardEncoder(transformation);
        ans = encoder.encodeString(input_text, encodedKeyboard);

        System.out.println(input_text);
        System.out.println(ans);

        
    }
}
