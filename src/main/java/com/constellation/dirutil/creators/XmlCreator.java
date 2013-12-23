package com.constellation.dirutil.creators;

import com.constellation.dirutil.vo.Dir;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/18/13
 * Time: 1:08 PM
 */
public interface XmlCreator {
    public void createXMLFile(Dir rootDir, String filename) throws JAXBException, IOException;
}
