package com.constellation.dirutil;

import com.constellation.dirutil.vo.Dir;
import com.constellation.dirutil.vo.FileObj;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/20/13
 * Time: 5:22 PM
 */
public class JAXBXMLCreator implements XmlCreator {

    @Override
    public void createXMLFile(String filename, File rootDir) throws JAXBException {
        File output = new File(filename+".xml");
        try {
            if(!output.exists()){
                output.createNewFile();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        Dir startDir = new Dir();
        startDir.setName(rootDir.getName());

        addChildren(rootDir, startDir);


            JAXBContext content = JAXBContext.newInstance(Dir.class);
            Marshaller marshaller = content.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(startDir, output);

    }

    private void addChildren(File rootDir, Dir dir){

        Dir newDir = new Dir();

        for(File f : getChildrenDirs(rootDir)){
            newDir.setName(f.getName());
            dir.getDirectories().add(newDir);
            addChildren(f, newDir);
        }

        for(File f : getFiles(rootDir)){
            FileObj file = new FileObj();
            file.setName(f.getName());
            file.setExtension(getExtention(f));
            dir.getFiles().add(file);
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
