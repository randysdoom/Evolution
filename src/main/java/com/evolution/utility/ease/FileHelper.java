package com.evolution.utility.ease;

import com.evolution.utility.LogHelper;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.nio.charset.Charset;

public class FileHelper
{
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static class Read
    {

        public static InputStream getInputStream(File f)
        {
            try{ return new FileInputStream(f); }
            catch(Exception ex){ writeException(ex); return null; }
        }

        public static BufferedReader getBufferedReader(File f){ return new BufferedReader(new InputStreamReader(getInputStream(f))); }

        public static String getFileContentsToString(File f){ return getFileContentsToString(getBufferedReader(f)); }

        public static String getFileContentsToString(BufferedReader br)
        {
            try
            {
                StringBuilder sb = new StringBuilder();
                String ln;
                while((ln = br.readLine()) != null){ sb.append(ln); }
                br.close();
                return sb.toString();
            }catch(Exception ex){ writeException(ex); return null; }
        }

    }

    public static class Write
    {

        public static OutputStream getOutputStream(File f)
        {
            try{ return new FileOutputStream(f); }
            catch(FileNotFoundException ex){ writeException(ex); return null; }
        }

        public static BufferedWriter getBufferedWriter(File f)
        {
            try{ return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f))); }
            catch(Exception ex){ writeException(ex); return null; }
        }

        public static void writeToFile(File f, String toWrite){ writeToFile(f, toWrite, false); }

        public static void writeToFile(File f, String toWrite, boolean append)
        {
            try
            {
                BufferedWriter bw = getBufferedWriter(f);
                if(append){ bw.append(toWrite); }else{ bw.write(toWrite); }
                bw.close();
            }catch(Exception ex){ writeException(ex); }
        }

    }

    public static boolean verifyFileExists(File f, boolean create)
    {
        if(f.exists()){ return true; }
        else if(!f.exists() && create){ createFile(f); if(f.exists()){ return true; } }
        return false;
    }

    public static void createFile(File f)
    {
        if(!verifyFileExists(f, false))
        {
            try{ f.getParentFile().mkdirs(); f.createNewFile(); }
            catch(Exception ex){ writeException(ex); }
        }
    }

    public static boolean isFileEmpty(File f)
    {
        if(verifyFileExists(f, false))
        {
            if(Read.getFileContentsToString(f).length() > 0)
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isJsonFile(File f)
    {
        if(f != null){ if(f.getName().contains(".json") || f.getAbsolutePath().contains(".json")){ return true; } }
        return false;
    }

    public static boolean isJarFile(File f)
    {
        if(f != null){ if(f.getName().contains(".jar") || f.getAbsolutePath().contains(".jar")){ return true; } }
        return false;
    }

    public static boolean isZipFile(File f)
    {
        if(f != null){ if(f.getName().contains(".zip") || f.getAbsolutePath().contains(".zip")){ return true; } }
        return false;
    }

    private static void writeException(Exception ex){ LogHelper.log(Level.ERROR, ex.getMessage()); }

}
