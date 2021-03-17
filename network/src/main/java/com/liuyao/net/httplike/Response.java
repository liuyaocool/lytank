package com.liuyao.net.httplike;

import com.liuyao.utils.IOUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Response {

    public static enum Status {
        OK(200, "ok"),

        ;

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        Status(int i, String ok) {
            this.code = i;
            this.msg = ok;
        }
    }
    private Socket socket;
    private Status status;

    public Response(Socket socket) {
        this.socket = socket;
        this.status = Status.OK;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void send(String msg) {
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            osw = new OutputStreamWriter(this.socket.getOutputStream());
            bw = new BufferedWriter(osw);
            bw.write("HTTP/1.1 " + this.status.getCode() + " " + this.status.getMsg() + "\n\n"
                    + msg + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(bw, osw, this.socket);
        }
    }
}
