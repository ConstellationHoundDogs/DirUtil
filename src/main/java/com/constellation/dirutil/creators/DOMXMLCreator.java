package com.constellation.dirutil.creators;

import com.constellation.dirutil.vo.Dir;
import com.constellation.dirutil.vo.FileObj;
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

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/18/13
 * Time: 9:16 PM
 */

public class DOMXMLCreator implements XmlCreator {

    @Override
    public void createXMLFile(Dir rootDir, String filename) throws IOException {

        File xmlFile = new File(filename+".xml");

        if(!xmlFile.exists()){
           xmlFile.createNewFile();
        }

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


    private void createDOMModel(Document doc, Dir dir, Element root){
        Element rootElement = doc.createElement("dir");
        rootElement.setAttribute("name", dir.getName());
        if(doc.hasChildNodes()){
            root.appendChild(rootElement);
        }else{
            doc.appendChild(rootElement);
        }
        for(Dir d : dir.getDirectories()){
            Element element = doc.createElement("dir");
            element.setAttribute("name", d.getName());
            createDOMModel(doc, d, rootElement);
        }
        for(FileObj f : dir.getFiles()){
            Element fileElement = doc.createElement("file");
            fileElement.setAttribute("name", f.getName());
            String extension = f.getExtension();
            fileElement.setAttribute("extention", extension);
            rootElement.appendChild(fileElement);
        }

    }

}
