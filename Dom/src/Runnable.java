import by.gsu.pmslab.ExchangeRate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Runnable {
    public static void main(String[] args) {
        String xml = "http://www.nbrb.by/Services/XmlExRates.aspx";
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder=factory.newDocumentBuilder();
            Document document= builder.parse(xml);
            document.getDocumentElement().normalize();
            System.out.println("Курс на "+ new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
            NodeList nodeList=document.getElementsByTagName("Currency");
            List<ExchangeRate> exchangeRateList=new ArrayList<ExchangeRate>();
            for (int i=0;i<nodeList.getLength();i++){
                exchangeRateList.add(getExchange(nodeList.item(i)));
            }
            for (ExchangeRate exchangeRate:exchangeRateList){
                System.out.println(exchangeRate.toString());
            }
        } catch (Exception exc){
            exc.printStackTrace();
        }

    }

    private static ExchangeRate getExchange(Node item) {
        ExchangeRate exchangeRate=new ExchangeRate();
        if (item.getNodeType()== Node.ELEMENT_NODE){
            Element element=(Element) item;
            exchangeRate.setCharCode(getTagValue("CharCode",element));
            exchangeRate.setScale(Integer.parseInt(getTagValue("Scale",element)));
            exchangeRate.setName(getTagValue("Name",element));
            exchangeRate.setRate(Float.parseFloat(getTagValue("Rate",element)));
        }
        return exchangeRate;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList=element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node=nodeList.item(0);
        return node.getNodeValue();
    }
}
