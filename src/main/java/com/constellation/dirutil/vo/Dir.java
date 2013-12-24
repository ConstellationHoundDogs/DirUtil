package com.constellation.dirutil.vo;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Dir> directories = new HashSet<Dir>();

    @XmlElement(name = "file", type = FileObj.class)
    private Set<FileObj> files = new HashSet<FileObj>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Dir> getDirectories() {
        return directories;
    }

    public void setDirectories(Set<Dir> directories) {
        this.directories = directories;
    }

    public Set<FileObj> getFiles() {
        return files;
    }

    public void setFiles(Set<FileObj> files) {
        this.files = files;
    }
}
