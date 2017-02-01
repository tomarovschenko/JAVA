package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ) on 03.01.2017.
 */

@XmlRootElement(name = "ValCurs")
public class ValCurs {
    String date;
    String name;
    Valute [] valute;

    public String getDate() {
        return date;
    }
    @XmlAttribute(name="Date")
    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public Valute[] getValutes() {
        return valute;
    }
    @XmlElement(name="Valute")
    public void setValutes(Valute[] valute) {
        this.valute = valute;
    }

    public static double getRate(Valute[] val, String valuteFrom, String valuteTo){
        double indexFrom=0;
        double indexTo=0;
        for (int i=0; i<val.length; i++){
            if(val[i].getCharCode().equals(valuteFrom)){
                indexFrom=val[i].getIndex();
            }
            if(val[i].getCharCode().equals(valuteTo)){
                indexTo=val[i].getIndex();
            }
        }
        return indexFrom/indexTo;
    }
    public static ValCurs getQuotes(String date){
        ValCurs valCurs = null;
        try {

            URL url = null;
            try {
                url = new URL("http://cbr.ru/scripts/XML_daily.asp?date_req="+date);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            valCurs = (ValCurs) jaxbUnmarshaller.unmarshal(url);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return valCurs;
    }
}
