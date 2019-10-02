package by.gsu.pmslab;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {
    private List<ExchangeRate> exchangeRates=null;
    private ExchangeRate exchangeRate=null;
    private StringBuilder data=null;
    public List<ExchangeRate> getExchangeRates(){
        return exchangeRates;
    }
    boolean bCharCode = false;
    boolean bScale = false;
    boolean bName = false;
    boolean bRate = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Currency")) {
            // create a new Employee and put it in Map
            // initialize Employee object and set id attribute
            exchangeRate = new ExchangeRate();
            // initialize list
            if (exchangeRates == null)
                exchangeRates = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("Name")) {
            // set boolean values for fields, will be used in setting Employee variables
            bName = true;
        }else if (qName.equalsIgnoreCase("CharCode")) {
            // set boolean values for fields, will be used in setting Employee variables
            bCharCode = true;
        }else if (qName.equalsIgnoreCase("Scale")) {
            // set boolean values for fields, will be used in setting Employee variables
            bScale = true;
        }else if (qName.equalsIgnoreCase("Rate")) {
            // set boolean values for fields, will be used in setting Employee variables
            bRate = true;
        }
        // create the data container
        data = new StringBuilder();
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bCharCode) {
            // age element, set Employee age
            exchangeRate.setCharCode(data.toString());
            bCharCode = false;
        } else if (bScale) {
            exchangeRate.setScale(Integer.parseInt(data.toString()));
            bScale= false;
        } else if (bName) {
            exchangeRate.setName(data.toString());
            bName = false;
        } else if (bRate) {
            exchangeRate.setRate(Float.parseFloat(data.toString()));
            bRate = false;
        }
        if (qName.equalsIgnoreCase("Currency")){
            exchangeRates.add(exchangeRate);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(ch, start, length);
    }
}
