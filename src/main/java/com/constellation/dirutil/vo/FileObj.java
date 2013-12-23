package com.constellation.dirutil.vo;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/23/13
 * Time: 6:38 PM
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement(name = "file")
@XmlAccessorType(XmlAccessType.FIELD)
public class FileObj {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "extension")
    private String extension;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
