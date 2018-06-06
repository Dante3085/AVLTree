package com.util;

import java.io.*;

public class Util
{
    public static void writeToFile(String filename, String data)
    {
        //File file = new File(filename);
        try
        {
            OutputStream out = new FileOutputStream(filename);
            PrintWriter printWriter = new PrintWriter(out);
            printWriter.write(data);
            printWriter.flush();
            out.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
