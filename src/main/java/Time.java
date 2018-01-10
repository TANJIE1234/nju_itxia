import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static void main(String args[]){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(df.format(new Date()));
    }
}
