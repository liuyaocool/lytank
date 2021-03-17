package com.liuyao.net.httplike;

import com.liuyao.utils.IOUtil;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpParser;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {

    public static Pattern methodReg = Pattern.compile("(GET|PUT|POST|DELETE|OPTIONS|TRACE|HEAD)");
    public static String charset = "UTF-8";
    private String method;
    private HashMap<String, String> headers;
    private String body;

    public String getMethod() {
        return method;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public Request(Socket socket) {
        DataInputStream dis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            InputStream is = socket.getInputStream();

            // DataInputStream -> Char Float ...
            // InputStream -> bytes 二进制
            dis = new DataInputStream(is);
            isr = new InputStreamReader(dis, charset);

            br = new BufferedReader(isr);

            // GET /path HTTP/1.1
//            String line = br.readLine();
            String line = HttpParser.readLine(is, charset);
            Matcher m = methodReg.matcher(line);
            boolean b = m.find();
            String method = m.group();

            // header
            HashMap<String, String> headers = new HashMap<>();
            Header[] headers1 = HttpParser.parseHeaders(is);
            for (Header h : headers1) {
                headers.put(h.getName(), h.getValue());
            }

            // body
            StringBuilder body = new StringBuilder();
            byte[] buffer = new byte[1024];
            while (is.available() > 0) {
                is.read(buffer);
                body.append(buffer);
            }

            this.method = method;
            this.headers = headers;
            this.body = body.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            IOUtil.close(br, isr, dis);
        }

    }

}
