package com.constellation.dirutil.creators;

import com.constellation.dirutil.vo.Dir;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/20/13
 * Time: 5:22 PM
 */
public class JAXBXMLCreator implements XmlCreator {

    @Override
    public void createXMLFile(Dir rootDir, String filename) throws JAXBException, IOException {
        File output = new File(filename+".xml");
        if(!output.exists()){
             output.createNewFile();
        }

        JAXBContext content = JAXBContext.newInstance(Dir.class);
        Marshaller marshaller = content.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(rootDir, output);
    }
}
