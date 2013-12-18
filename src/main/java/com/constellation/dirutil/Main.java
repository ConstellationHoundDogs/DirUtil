package com.constellation.dirutil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/18/13
 * Time: 1:06 PM
 */
public class Main {

    public static final String rootDir = "/home/vladimir/IdeaProjects";

    public static void main(String[] args) {
        new Main().run();
    }

    private void run(){
        File rootDir = new File(this.rootDir);
        if(!rootDir.exists()){
            System.out.println("The directory does not exist.");
            System.exit(1);
        }
        if(!rootDir.isDirectory()){
            System.out.println("The file is not directory.");
            System.exit(1);
        }

        for(File f : getChildrenDirs(rootDir)){
            System.out.println(f.getName());
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



}
