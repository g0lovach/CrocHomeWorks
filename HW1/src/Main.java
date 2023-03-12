import java.util.Locale;

public class Main {

    public static void convertByteToHumanView(long byteView){

        /*
        Метод написан с допущением, что входные данные представляют собой
        исключительно положительные целые числа
         */

        String[] units = {" B", " KB", " MB", " GB", " TB", " PB", " EB"};
        double temp = (double)byteView;
        int power = 0;

        while (temp >= 1024){
            temp/=1024;
            power += 1;
        }

        System.out.println(String.format(Locale.ENGLISH,"%.1f", temp) +  units[power]);

    }

    public static void main(String[] args) {

    convertByteToHumanView(53692044905543L);

    }
}