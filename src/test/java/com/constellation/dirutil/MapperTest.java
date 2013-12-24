package com.constellation.dirutil;

import com.constellation.dirutil.vo.Dir;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 12/24/13
 * Time: 12:28 PM
 */
public class MapperTest {

    public final String rootDirPath = "/home/vladimir/onlineGitRepos/DirUtil/src/test/TestDir";

    @Test
    public void testMapPathNotNull() throws Exception {
        Mapper mapper = new Mapper();
        Dir rootDir = mapper.mapPath(rootDirPath);
        Assert.assertNotNull(rootDir);
    }


    @Test
    public void testMapPathNameNotNull() throws Exception {
        Mapper mapper = new Mapper();
        Dir rootDir = mapper.mapPath(rootDirPath);
        Assert.assertNotNull(rootDir.getName());
    }

    @Test
    public void testMapPathNameNotBlank() throws Exception {
        Mapper mapper = new Mapper();
        Dir rootDir = mapper.mapPath(rootDirPath);
        Assert.assertNotEquals(rootDir.getName(), "");
    }

    @Test
    public void testMapPathChildrenDirNotNull() throws Exception {
        Mapper mapper = new Mapper();
        Dir rootDir = mapper.mapPath(rootDirPath);
        Assert.assertNotNull(rootDir.getDirectories());
    }

    @Test
    public void testMapPathChildrenDirsNotEmpty() throws Exception {
        Mapper mapper = new Mapper();
        Dir rootDir = mapper.mapPath(rootDirPath);
        Assert.assertFalse(rootDir.getDirectories().isEmpty());
    }

    @Test
    public void testMapPathFilesNotNull() throws Exception {
        Mapper mapper = new Mapper();
        Dir rootDir = mapper.mapPath(rootDirPath);
        Assert.assertNotNull(rootDir.getFiles());
    }

    @Test
    public void testMapPathFilesNotEmpty() throws Exception {
        Mapper mapper = new Mapper();
        Dir rootDir = mapper.mapPath(rootDirPath);
        Assert.assertFalse(rootDir.getFiles().isEmpty());
    }

    @Test
    public void testMapPathChildrenOfChildrenNotEmpty() throws Exception {
        Mapper mapper = new Mapper();
        Dir rootDir = mapper.mapPath(rootDirPath);
        Assert.assertFalse(rootDir.getDirectories().isEmpty());
    }

}
