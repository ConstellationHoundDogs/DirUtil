package com.constellation.dirutil;

import com.sun.xml.internal.stream.events.StartElementEvent;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
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

            eventWriter.add(eventFactory.createStartElement("", "", rootDir.getName()));



            eventWriter.add(eventFactory.createEndElement("", "", rootDir.getName()));
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();

        } catch (XMLStreamException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void writeChildren(XMLEventWriter writer, File rootFile){
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");



    }


    private List<File> getChildrenDirs(File dir){
        List<File> dirs = new ArrayList<File>();
        for(File f : dir.listFiles()){
            if(f.isDirectory()){
                dirs.add(f);
            }
        }
        return dirs;
    }

    private List<File> getFiles(File dir){
        List<File> files = new ArrayList<File>();
        for(File f : dir.listFiles()){
            if(f.isFile()){
                files.add(f);
            }
        }
        return files;
    }



}
