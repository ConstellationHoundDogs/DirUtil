package com.constellation.dirutil;

import com.constellation.dirutil.vo.Dir;
import com.constellation.dirutil.vo.FileObj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/24/13
 * Time: 12:45 AM
 */
public class Mapper {

    /**
     *
     * @param dirName name of directory to mapped
     * @return mapped directory
     * @throws FileNotFoundException  - if the given fileName does not correspond to a existing directory
     * @throws IllegalArgumentException  - if the given fileName correspond to a file
     */
    public Dir mapPath(String dirName) throws FileNotFoundException {
        File rootDirFile = new File(dirName);

        if(!rootDirFile.exists()){
            throw new FileNotFoundException("The file does not exist.");
        }
        if(!rootDirFile.isDirectory()){
            throw new IllegalArgumentException("The file is not a directory.");
        }

        Dir rootDir = new Dir();
        rootDir.setName(dirName);
        addChildren(rootDirFile, rootDir);
        return rootDir;
    }


    private void addChildren(File rootDir, Dir dir){

        for(File f : getChildrenDirs(rootDir)){
            Dir newDir = new Dir();
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
