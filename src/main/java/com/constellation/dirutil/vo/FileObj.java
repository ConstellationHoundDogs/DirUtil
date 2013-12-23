package com.constellation.dirutil.vo;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/23/13
 * Time: 6:38 PM
 */

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "file")
public class FileObj {

    @XmlAttribute
    private String name;
    @XmlAttribute
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
