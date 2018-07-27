package com.sunat.sunasis.utils;
import java.nio.charset.Charset;

public class AppUtils {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String SEPARATOR = "|";
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final String URL_FILE = "http://www2.sunat.gob.pe/padron_reducido_ruc.zip";
    public static final String FILE_NAME = "sunat.zip";
    public static final String UNZIP_FILE_NAME = "sunat.txt";
    public static final int MAX_THREADS = 10;
    public static final int CHUNK_ZISE_COMPANIES = 50000;
    public static final int CHUNK_ZISE_FILES = 1;
}