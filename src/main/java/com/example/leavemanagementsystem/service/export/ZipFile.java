package com.example.leavemanagementsystem.service.export;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {
    private List<String> fileList;
    private static final String SOURCE_FOLDER = "src/main/resources/word/export"; // SourceFolder path
//    private static final String SOURCE_FOLDER = "D:/test";// SourceFolder path
    public ZipFile() {
        fileList = new ArrayList<String>();
    }

    public void zipIt(String zipFile, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        String source = new File(SOURCE_FOLDER).getName();

        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(outputStream);
            System.out.println("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file: this.fileList) {
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
                    int len;
                    while ((len = in .read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();
            System.out.println("Folder successfully compressed");


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        outputStream.close();
    }

    public void generateFileList(File node) {
        // add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename: subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }


    private String generateZipEntry(String file) {
        return file.substring(SOURCE_FOLDER.length() + 1, file.length());
    }
    public void deleteFile(){
        File file = new File(SOURCE_FOLDER);
        File[] files = file.listFiles();
        for(File filess: files) {
            if (!filess.isDirectory())
                filess.delete();
        }
        System.out.println("Xoa file Local thanh cong");
    }



}
