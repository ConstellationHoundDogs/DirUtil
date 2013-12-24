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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/24/13
 * Time: 10:10 PM
 */
public class StAXXMLCreatorTest {

    public static final String dirPath = "/home/vladimir/onlineGitRepos/DirUtil/src/test/TestDir";
    public static Dir testDir;

    @BeforeClass
    public static void initDir() throws FileNotFoundException {
        Mapper mapper = new Mapper();
        testDir = mapper.mapPath(dirPath);
    }

    @Test
    public void createXMLFileTestExist() throws JAXBException, IOException {
        StAXXMLCreator stAXXMLCreator = new StAXXMLCreator();
        stAXXMLCreator.createXMLFile(testDir, "TestXMLFile");
        File testFile = new File("/home/vladimir/onlineGitRepos/DirUtil/TestXMLFile.xml");
        Assert.assertTrue(testFile.exists());
    }

    @Test
    public void createXMLFileTestIsFile() throws JAXBException, IOException {
        StAXXMLCreator stAXXMLCreator = new StAXXMLCreator();
        stAXXMLCreator.createXMLFile(testDir, "TestXMLFile");
        File testFile = new File("/home/vladimir/onlineGitRepos/DirUtil/TestXMLFile.xml");
        Assert.assertTrue(testFile.isFile());
    }

    @Test
    public void createXMLFileTestWellFormedness() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        StAXXMLCreator stAXXMLCreator = new StAXXMLCreator();
        stAXXMLCreator.createXMLFile(testDir, "TestXMLFile");
        File testFile = new File("/home/vladimir/onlineGitRepos/DirUtil/TestXMLFile.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.parse(new InputSource(new FileReader(testFile)));
    }
}
