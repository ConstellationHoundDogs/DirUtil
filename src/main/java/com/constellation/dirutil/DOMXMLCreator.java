package com.constellation.dirutil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/18/13
 * Time: 9:16 PM
 */
public class DOMXMLCreator implements XmlCreator {

    @Override
    public void createXMLFile(String filename, File rootDir) {

        File xmlFile = new File(filename+".xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();

            Element rootElem = doc.createElement("dir");
            rootElem.setAttribute("name", rootDir.getName());

            createDOMModel(doc, rootDir, rootElem);

            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Result dest = new StreamResult(new FileWriter(xmlFile));
            aTransformer.transform(source, dest);

        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        } catch (TransformerConfigurationException e) {
            System.err.println();
        } catch (TransformerException e) {
            System.err.println();
        } catch (IOException e) {
            System.err.println();
        }
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

    private void createDOMModel(Document doc, File file, Element root){
        Element rootElement = doc.createElement("dir");
        rootElement.setAttribute("name", file.getName());
        if(doc.hasChildNodes()){
            root.appendChild(rootElement);
        }else{
            doc.appendChild(rootElement);
        }
        for(File d : getChildrenDirs(file)){
            Element element = doc.createElement("dir");
            element.setAttribute("name", d.getName());
            createDOMModel(doc, d, rootElement);
        }
        for(File f : getFiles(file)){
            Element fileElement = doc.createElement("file");
            fileElement.setAttribute("name", f.getName());
            String extension = getExtention(f);
            fileElement.setAttribute("extention", extension);
            rootElement.appendChild(fileElement);
        }

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
}
