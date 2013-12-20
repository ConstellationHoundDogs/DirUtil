package com.constellation.dirutil;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartDocument;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    public void createXMLFile(String filename, File rootDir) {

        File outputFile = new File(filename + ".xml");

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

    private void writeChildren(XMLEventWriter writer, File rootFile) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        List<Attribute> attributes = new ArrayList<>();
        Attribute attr = eventFactory.createAttribute("name", rootFile.getName());
        attributes.add(attr);

        writer.add(eventFactory.createStartElement("", "", "dir", attributes.iterator(), null));

        for(File f : getChildrenDirs(rootFile)){
            writeChildren(writer, f);
        }

        for(File f : getFiles(rootFile)){
            List<Attribute> fileAttributes = new ArrayList<>();
            fileAttributes.add(eventFactory.createAttribute("name", f.getName()));
            fileAttributes.add(eventFactory.createAttribute("extention", getExtention(f)));

            writer.add(eventFactory.createStartElement("", "", "file", fileAttributes.iterator(), null));
            writer.add(eventFactory.createEndElement("", "", "file"));

        }

        writer.add(eventFactory.createEndElement("", "", "dir"));
    }

    private String getExtention(File f) {
        String extension = "";
        String fileName = f.getName();
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    private List<File> getChildrenDirs(File dir){
        List<File> dirs = new ArrayList<>();
        for(File f : dir.listFiles()){
            if(f.isDirectory()){
                dirs.add(f);
            }
        }
        return dirs;
    }

    private List<File> getFiles(File dir){
        List<File> files = new ArrayList<>();
        for(File f : dir.listFiles()){
            if(f.isFile()){
                files.add(f);
            }
        }
        return files;
    }
}
