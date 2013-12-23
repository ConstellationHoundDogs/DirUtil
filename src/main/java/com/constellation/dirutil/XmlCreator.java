package com.constellation.dirutil;

import javax.xml.bind.JAXBException;
import java.io.File;
/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/18/13
 * Time: 1:08 PM
 */
public interface XmlCreator {
    public void createXMLFile(String filename, File rootDir) throws JAXBException;
}
