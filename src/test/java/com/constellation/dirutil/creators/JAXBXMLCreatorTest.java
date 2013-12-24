package com.constellation.dirutil.creators;

import com.constellation.dirutil.Mapper;
import com.constellation.dirutil.vo.Dir;
import org.junit.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/24/13
 * Time: 12:48 PM
 */
public class JAXBXMLCreatorTest {

    public static final String dirPath = "/home/vladimir/onlineGitRepos/DirUtil/src/test/TestDir";
    public static Dir testDir;

    @BeforeClass
    public static void initDir() throws FileNotFoundException {
        Mapper mapper = new Mapper();
        testDir = mapper.mapPath(dirPath);
    }

    @Test
    public void createXMLFileTestExist() throws JAXBException, IOException {
        JAXBXMLCreator jaxbxmlCreator = new JAXBXMLCreator();
        jaxbxmlCreator.createXMLFile(testDir, "TestXMLFile");
        File testFile = new File("/home/vladimir/onlineGitRepos/DirUtil/TestXMLFile.xml");
        Assert.assertTrue(testFile.exists());
    }

    @Test
    public void createXMLFileTestIsFile() throws JAXBException, IOException {
        JAXBXMLCreator jaxbxmlCreator = new JAXBXMLCreator();
        jaxbxmlCreator.createXMLFile(testDir, "TestXMLFile");
        File testFile = new File("/home/vladimir/onlineGitRepos/DirUtil/TestXMLFile.xml");
        Assert.assertTrue(testFile.isFile());
    }

    @Test
    public void createXMLFileTestWellFormedness() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        JAXBXMLCreator jaxbxmlCreator = new JAXBXMLCreator();
        jaxbxmlCreator.createXMLFile(testDir, "TestXMLFile");
        File testFile = new File("/home/vladimir/onlineGitRepos/DirUtil/TestXMLFile.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.parse(new InputSource(new FileReader(testFile)));
    }

}
