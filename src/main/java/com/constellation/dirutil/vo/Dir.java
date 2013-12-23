package com.constellation.dirutil.vo;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/23/13
 * Time: 6:38 PM
 */
@XmlRootElement(name = "dir")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dir {

    @XmlAttribute
    private String name;

    @XmlElement(name = "dir", type = Dir.class)
    private List<Dir> directories = new ArrayList<>();

    @XmlElement(name = "file", type = FileObj.class)
    private List<FileObj> files = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dir> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Dir> directories) {
        this.directories = directories;
    }

    public List<FileObj> getFiles() {
        return files;
    }


    public void setFiles(List<FileObj> files) {
        this.files = files;
    }

}
