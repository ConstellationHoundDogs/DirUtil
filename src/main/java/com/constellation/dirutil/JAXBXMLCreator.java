package com.constellation.dirutil;

import com.constellation.dirutil.vo.Dir;

import java.io.File;
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
    public void createXMLFile(String filename, File rootDir) {
        File output = new File(filename);

        Dir startDir = new Dir();
        startDir.setName(rootDir.getName());

    }

    private void addChildren(File rootDir, Dir dir){

        for(File f : getChildrenDirs(rootDir)){
            Dir newDir = new Dir();
            newDir.setName(f.getName());
            dir.getDirs().add(newDir);
            addChildren(f, newDir);
        }

        for(File f : getFiles(rootDir)){


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
