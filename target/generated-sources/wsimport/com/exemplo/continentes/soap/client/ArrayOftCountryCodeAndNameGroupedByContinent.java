
package com.exemplo.continentes.soap.client;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ArrayOftCountryCodeAndNameGroupedByContinent complex type.</p>
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ArrayOftCountryCodeAndNameGroupedByContinent">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="tCountryCodeAndNameGroupedByContinent" type="{http://www.oorsprong.org/websamples.countryinfo}tCountryCodeAndNameGroupedByContinent" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOftCountryCodeAndNameGroupedByContinent", propOrder = {
    "tCountryCodeAndNameGroupedByContinent"
})
public class ArrayOftCountryCodeAndNameGroupedByContinent {

    @XmlElement(nillable = true)
    protected List<TCountryCodeAndNameGroupedByContinent> tCountryCodeAndNameGroupedByContinent;

    /**
     * Gets the value of the tCountryCodeAndNameGroupedByContinent property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tCountryCodeAndNameGroupedByContinent property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTCountryCodeAndNameGroupedByContinent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TCountryCodeAndNameGroupedByContinent }
     * </p>
     * 
     * 
     * @return
     *     The value of the tCountryCodeAndNameGroupedByContinent property.
     */
    public List<TCountryCodeAndNameGroupedByContinent> getTCountryCodeAndNameGroupedByContinent() {
        if (tCountryCodeAndNameGroupedByContinent == null) {
            tCountryCodeAndNameGroupedByContinent = new ArrayList<>();
        }
        return this.tCountryCodeAndNameGroupedByContinent;
    }

}
