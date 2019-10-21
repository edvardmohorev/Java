
import by.gsu.pmslab.classes.Values;
import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Runner {
    private static String SAVE = "src/main/resources/in.json";
    private static String URL="http://api.openweathermap.org/data/2.5/weather?q=Homyel%20&mode=json%20&appid=71157ec6be432ca0a3fd4df79cd083c4";
    public static void main(String[] args) throws IOException {
        loadJSON();
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(SAVE));
        Values string = gson.fromJson(br, Values.class);
        String html="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"widget-right weather-right--type1 widget-right--brown\">\n" +
                "    <div class=\"widget-right__header widget-right__header--brown\">\n" +
                "        <div class=\"widget-right__layout\">\n" +
                "            <div>\n" +
                "                <h2 class=\"widget-right__title\">"+string.getName()+"</h2>\n" +
                "                <p class=\"widget-right__description\">"+string.getExtended_desc()+"</p>\n" +
                "            </div>\n" +
                "        </div><img width=\"128\" height=\"128\" class=\"weather-right__icon weather-right__icon--type1\" alt=\"Weather in "+string.getName()+"\" src=\"//openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/"+string.getIcon()+".png\">\n" +
                "    </div>\n" +
                "    <div class=\"weather-right weather-right--brown\">\n" +
                "        <div class=\"weather-right__layout\">\n" +
                "            <div class=\"weather-right__temperature\">"+string.getTemp()+"<span>°C</span></div>\n" +
                "            <div class=\"weather-right__weather\">\n" +
                "                <div class=\"weather-right-card\">\n" +
                "                    <table class=\"weather-right__table\">\n" +
                "                        <tbody><tr class=\"weather-right__items\">\n" +
                "                            <th class=\"weather-right__item\" colspan=\"2\">Details</th>\n" +
                "                        </tr>\n" +
                "                        <tr class=\"weather-right__items\">\n" +
                "                            <td class=\"weather-right__item\">Min temperature</td>\n" +
                "                            <td class=\"weather-right__item weather-right__feels\">"+string.getMin_temp()+"<span>°C</span></td>\n" +
                "                        </tr>\n" +
                "                        <tr class=\"weather-right__items\">\n" +
                "                            <td class=\"weather-right__item\">Max temperature</td>\n" +
                "                            <td class=\"weather-right__item weather-right__feels\">"+string.getMax_temp()+"<span>°C</span></td>\n" +
                "                        </tr>\n" +
                "                        <tr class=\"weather-right__items\">\n" +
                "                            <td class=\"weather-right__item\">Wind</td>\n" +
                "                            <td class=\"weather-right__item weather-right__wind-speed\">"+string.getSpeed()+" m/s </td>\n" +
                "                        </tr>\n" +
                "                        <tr class=\"weather-right-card__items\">\n" +
                "                            <td class=\"weather-right__item\">Humidity</td>\n" +
                "                            <td class=\"weather-right__item weather-right__humidity\">"+string.getHumidity()+"%</td>\n" +
                "                        </tr>\n" +
                "                        <tr class=\"weather-right-card__items\">\n" +
                "                            <td class=\"weather-right__item\">Precip</td>\n" +
                "                            <td class=\"weather-right__item\"></td>\n" +
                "                        </tr>\n" +
                "                        <tr class=\"weather-right-card__items\">\n" +
                "                            <td class=\"weather-right__item\">Pressure</td>\n" +
                "                            <td class=\"weather-right__item weather-right__pressure\">"+string.getPressure()+" hPa</td>\n" +
                "                        </tr>\n" +
                "                        </tbody></table>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"widget-right__footer widget-right__footer--brown\">\n" +
                "        <div class=\"widget-right__layout\"><a class=\"widget-right__link\" href=\"//openweathermap.org/\" target=\"_blank\">OpenWeatherMap</a>\n" +
                "            <div class=\"widget-right__date\">"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd-MM-yyyy")) +"</div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        FileOutputStream fileOutputStream=new FileOutputStream("src/main/resources/widget.html");
        fileOutputStream.write(html.getBytes());
        fileOutputStream.close();
    }
    private static void loadJSON() throws IOException {
        java.net.URL url=new URL(URL);
        BufferedInputStream bf=new BufferedInputStream(url.openStream());
        FileOutputStream fileOutputStream=new FileOutputStream(SAVE);
        byte[] bytes=new byte[1024];
        int count=0;
        while ((count=bf.read(bytes,0,1024))!=-1){
            fileOutputStream.write(bytes,0,count);
        }
        fileOutputStream.close();
        bf.close();
    }
}
