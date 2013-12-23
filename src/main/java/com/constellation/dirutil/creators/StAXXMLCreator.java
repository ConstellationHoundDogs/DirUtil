package com.constellation.dirutil.creators;

import com.constellation.dirutil.vo.Dir;
import com.constellation.dirutil.vo.FileObj;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartDocument;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/20/13
 * Time: 3:45 PM
 */

public class StAXXMLCreator implements XmlCreator {

    @Override
    public void createXMLFile(Dir rootDir, String filename) throws IOException {

        File outputFile = new File(filename + ".xml");


        if(!outputFile.exists()){
            outputFile.createNewFile();
        }

        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        try {
            XMLEventWriter eventWriter = factory.createXMLEventWriter(new FileOutputStream(outputFile));
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            StartDocument startDocument = eventFactory.createStartDocument();

            eventWriter.add(startDocument);

            writeChildren(eventWriter, rootDir);

            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();

        } catch (XMLStreamException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void writeChildren(XMLEventWriter writer, Dir rootFile) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        List<Attribute> attributes = new ArrayList<>();
        Attribute attr = eventFactory.createAttribute("name", rootFile.getName());
        attributes.add(attr);

        writer.add(eventFactory.createStartElement("", "", "dir", attributes.iterator(), null));

        for(Dir d : rootFile.getDirectories()){
            writeChildren(writer, d);
        }

        for(FileObj f : rootFile.getFiles()){
            List<Attribute> fileAttributes = new ArrayList<>();
            fileAttributes.add(eventFactory.createAttribute("name", f.getName()));
            fileAttributes.add(eventFactory.createAttribute("extention", f.getExtension()));

            writer.add(eventFactory.createStartElement("", "", "file", fileAttributes.iterator(), null));
            writer.add(eventFactory.createEndElement("", "", "file"));

        }

        writer.add(eventFactory.createEndElement("", "", "dir"));
    }

}
