import by.gsu.pmslab.ExchangeRate;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runnable {
    public static void main(String[] args) {
        String url="StAX\\src\\main.xml";
        List<ExchangeRate> exchangeRateList=parseXMLFile(url);
        for (ExchangeRate exchangeRate:exchangeRateList){
            System.out.println(exchangeRate);
        }
    }
    private static List<ExchangeRate> parseXMLFile(String url) {
        List<ExchangeRate> exchangeRateList=new ArrayList<>();
        ExchangeRate exchangeRate=null;
        XMLInputFactory xmlInputFactory=XMLInputFactory.newInstance();
        try {
            XMLEventReader reader=xmlInputFactory.createXMLEventReader(new FileInputStream(url));
            while (reader.hasNext()){
                XMLEvent xmlEvent=reader.nextEvent();
                if (xmlEvent.isStartElement()){
                    StartElement startElement=xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Currency"))
                        exchangeRate=new ExchangeRate();
                    if (startElement.getName().getLocalPart().equals("CharCode")){
                        xmlEvent=reader.nextEvent();
                        exchangeRate.setCharCode(xmlEvent.asCharacters().getData());
                    }if (startElement.getName().getLocalPart().equals("Scale")){
                        xmlEvent=reader.nextEvent();
                        exchangeRate.setScale(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }if (startElement.getName().getLocalPart().equals("Name")){
                        xmlEvent=reader.nextEvent();
                        exchangeRate.setName(xmlEvent.asCharacters().getData());
                    }if (startElement.getName().getLocalPart().equals("Rate")){
                        xmlEvent=reader.nextEvent();
                        exchangeRate.setRate(Float.parseFloat(xmlEvent.asCharacters().getData()));
                    }
                }
                if (xmlEvent.isEndElement()){
                    EndElement endElement=xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Currency")){
                        exchangeRateList.add(exchangeRate);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return exchangeRateList;
    }
}
