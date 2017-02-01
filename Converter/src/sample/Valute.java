package sample;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by ) on 03.01.2017.
 */

@XmlRootElement(name="Valute")
@XmlType(propOrder ={"id","numCode","charCode","nominal","name","value"})
public class Valute {
    String id;
    int numCode;
    String charCode;
    int nominal;
    String name;
    String value;

    public String getId() {
        return id;
    }
    @XmlAttribute(name="ID")
    public void setId(String id) {
        this.id = id;
    }

    public int getNumCode() {
        return numCode;
    }
    @XmlElement(name="NumCode")
    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }
    @XmlElement(name="CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }
    @XmlElement(name="Nominal")
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }
    @XmlElement(name="Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    @XmlElement(name="Value")
    public void setValue(String value) {
        this.value = value;
    }
    public double getIndex(){
        String val=value.replace(",",".");
        double index=Double.parseDouble(val)/nominal;
        return index;
    }


}
