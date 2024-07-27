/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quangnam.laptrinhmang.Bai1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author coconut
 */
public class CopyFile {
    
    public static void main(String[] args)
    {
        //System.out.println("Current working directory: " + System.getProperty("user.dir"));

//        copyFile("src/main/java/com/quangnam/laptrinhmang/Bai1/sample.mp4"
//                , "src/main/java/com/quangnam/laptrinhmang/Bai1/copySameple.mp4");
        Path  sourceDir = Paths.get("src/main/java/com/quangnam/laptrinhmang/Bai1/sourceDir");
        Path destDir = Paths.get("src/main/java/com/quangnam/laptrinhmang/destDir");
        
        try
        {
            if (!Files.exists(destDir))
            {
                Files.createDirectories(destDir);
            }
            List<Path> files = Files.list(sourceDir)
                                    .filter(Files::isRegularFile)
                                    .toList();
        }
        catch (IOException excep)
        {
        }
        
        System.out.println("copy sucessfully!");
    }
    public static class FileCopyTask implements Runnable
    {
        private Path sourceFile;
        private Path destDir;
        
        public FileCopyTask(Path sourceFile , Path destDir)
        {
            this.sourceFile = sourceFile;
            this.destDir = destDir;
        }
        @Override
        public void run()
        {
            try
            {
                Path destFile = destDir.resolve(sourceFile.getFileName());
                Files.copy(sourceFile, destFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied: " + sourceFile.getFileName());
            }
            catch (IOException ex)
            {
                
            }
        }
    }
    
    public static void copyFile(String sourcePath , String destPath)
    {
        File dest = new File(destPath);
        
        try 
        {
            if (!dest.exists())
                dest.createNewFile();
            try ( BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourcePath));
                  BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destPath));
                )
            {
                byte[] buffer = new byte[1024 * 1024];
                int byteRead;
                while ((byteRead = bis.read(buffer)) != -1)
                {
                    bos.write(buffer, 0, byteRead);
                }
            } 
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch( IOException fe)
        {
            
        }
    }
}
