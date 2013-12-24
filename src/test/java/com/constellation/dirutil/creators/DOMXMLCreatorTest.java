package com.constellation.dirutil.creators;

import com.constellation.dirutil.Mapper;
import com.constellation.dirutil.vo.Dir;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/24/13
 * Time: 7:42 PM
 */
public class DOMXMLCreatorTest {

    public static final String dirPath = "/home/vladimir/onlineGitRepos/DirUtil/src/test/TestDir";
    public static Dir testDir = null;

    @BeforeClass
    public static void init() throws FileNotFoundException {
        Mapper mapper = new Mapper();
        testDir = mapper.mapPath(dirPath);
    }

    @Test
    public void createXmlFileTestExists() throws IOException {
        DOMXMLCreator domxmlCreator = new DOMXMLCreator();
        domxmlCreator.createXMLFile(testDir, "XMLFileTest");
        File testFile = new File(dirPath);
        Assert.assertTrue(testFile.exists());
    }

    @Test
    public void createXmlFileTestIsFile() throws IOException {
        DOMXMLCreator domxmlCreator = new DOMXMLCreator();
        domxmlCreator.createXMLFile(testDir, "XMLFileTest");
        File testFile = new File("/home/vladimir/onlineGitRepos/DirUtil/TestXMLFile.xml");
        Assert.assertTrue(testFile.isFile());
    }

    @Test
    public void createXMLFileTestWellFormedness() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        DOMXMLCreator jaxbxmlCreator = new DOMXMLCreator();
        jaxbxmlCreator.createXMLFile(testDir, "TestXMLFile");
        File testFile = new File("/home/vladimir/onlineGitRepos/DirUtil/TestXMLFile.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.parse(new InputSource(new FileReader(testFile)));
    }

}
