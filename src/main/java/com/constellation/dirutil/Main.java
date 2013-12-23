package com.constellation.dirutil;

import com.constellation.dirutil.creators.DOMXMLCreator;
import com.constellation.dirutil.creators.StAXXMLCreator;
import com.constellation.dirutil.creators.XmlCreator;
import com.constellation.dirutil.vo.Dir;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/18/13
 * Time: 1:06 PM
 */
public class Main {

    public static final String rootDir = "/home/vladimir/IdeaProjects";

    public static void main(String[] args) throws JAXBException {
        new Main().run();
    }

    private void run() throws JAXBException {

        Mapper mapper = new Mapper();
        Dir rootDir = null;
        try {
            rootDir = mapper.mapPath(this.rootDir);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        XmlCreator creator = new DOMXMLCreator();
        try {
            creator.createXMLFile(rootDir, "XMLFile");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}