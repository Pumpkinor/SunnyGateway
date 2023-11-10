package test;

import okhttp3.ResponseBody;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MyGzipUtil {
    public static byte[] gzip(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toByteArray();
    }

    public static String unGzip(ResponseBody responseBody) throws IOException {
        // 创建 GZIPInputStream 对象，用于解压缩响应体
        GZIPInputStream gis = new GZIPInputStream(responseBody.byteStream());
// 使用 BufferedReader 读取解压后的数据
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
// 用来保存解压后的数据
        StringBuilder decompressed = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            decompressed.append(line);
        }
// 输出解压后的数据
        return decompressed.toString();
    }

    public static String decompress(byte[] gzipBytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(gzipBytes);
        GZIPInputStream gzipInputStream = new GZIPInputStream(bais);
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = gzipInputStream.read(buffer)) > 0) {
            sb.append(new String(buffer, 0, n, "UTF-8"));
        }
        return sb.toString();
    }
}
