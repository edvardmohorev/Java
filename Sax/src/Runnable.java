import by.gsu.pmslab.ExchangeRate;
import by.gsu.pmslab.MyHandler;
import org.xml.sax.SAXException;

import javax.security.sasl.SaslException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Runnable {
    public static void main(String[] args) {
        SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
        System.out.println(new SimpleDateFormat("dd:MM:yyyy").format(new Date()));
        try {
            SAXParser saxParser=saxParserFactory.newSAXParser();
            MyHandler handler=new MyHandler();
            saxParser.parse("http://www.nbrb.by/Services/XmlExRates.aspx",handler);
            List<ExchangeRate> exchangeRateList=handler.getExchangeRates();
            for (ExchangeRate exchangeRate:exchangeRateList){
                System.out.println(exchangeRate);
            }
        }catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
