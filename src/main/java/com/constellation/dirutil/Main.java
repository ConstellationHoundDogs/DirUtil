package com.constellation.dirutil;

import java.io.File;

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

        XmlCreator xmlCreator = new DOMXMLCreator();
        xmlCreator.createXMLFile("XMLFile", rootDir);
    }
}
