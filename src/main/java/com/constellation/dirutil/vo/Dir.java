package com.constellation.dirutil.vo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/23/13
 * Time: 6:38 PM
 */
@XmlRootElement(name = "dir")
public class Dir {

    @XmlAttribute
    private String name;

    @XmlElement
    private List<Dir> dirs = new ArrayList<>();

    @XmlElement
    private List<FileObj> files = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dir> getDirs() {
        return dirs;
    }

    public void setDirs(List<Dir> dirs) {
        this.dirs = dirs;
    }

    public List<FileObj> getFiles() {
        return files;
    }


    public void setFiles(List<FileObj> files) {
        this.files = files;
    }

}
