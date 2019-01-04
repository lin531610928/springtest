package com.zilin.springtest.service;

import com.zilin.springtest.entity.PrintDto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class PrintS2 {
    private static final byte H2_BIT_ASCII          = 0x10;
    private static final byte W2_BIT_ASCII          = 0x20;
    private static final byte H2_BIT_HZ             = 0x08;
    private static final byte W2_BIT_HZ             = 0x04;
    private static final byte UNDER_LINE_BIT     = (byte)0x80;
    private static final byte LF                 = '\n';

    private static final byte[] INTI_PRINT           = new byte[]{ 0x1B, '@'};
    private static final byte[] CHAR_SET_CHINESE     = new byte[]{ 0x1B, 'R',   15 };

    private static final byte[] CHAR_SET_HZ          = new byte[]{ 0x1C, '&' };
    private static final byte[] NORMAL_MODE          = new byte[]{ 0x1C, '!', 0x00,
            0x1B, '!', 0x00,
    };
    private static final byte[] H2_MODE              = new byte[]{ 0x1C, '!', H2_BIT_HZ,
            0x1B, '!', H2_BIT_ASCII,
    };
    private static final byte[] W2_MODE              = new byte[]{ 0x1C, '!', W2_BIT_HZ,
            0x1B, '!', W2_BIT_ASCII,
    };
    private static final byte[] H2W2_MODE            = new byte[]{ 0x1C, '!', H2_BIT_HZ|W2_BIT_HZ,
            0x1B, '!', H2_BIT_ASCII|W2_BIT_ASCII,
    };
    private static final byte[] UNDER_LINE_MODE      = new byte[]{ 0x1C, '!', UNDER_LINE_BIT,
            0x1B, '!', UNDER_LINE_BIT,
    };
    private static final byte[] H2_UNDER_LINE_MODE   = new byte[]{ 0x1C, '!', H2_BIT_HZ|UNDER_LINE_BIT,
            0x1B, '!', H2_BIT_ASCII|UNDER_LINE_BIT,
    };
    private static final byte[] W2_UNDER_LINE_MODE   = new byte[]{ 0x1C, '!', W2_BIT_HZ|UNDER_LINE_BIT,
            0x1B, '!', W2_BIT_ASCII|UNDER_LINE_BIT,
    };
    private static final byte[] H2W2_UNDER_LINE_MODE = new byte[]{ 0x1C, '!', H2_BIT_HZ|W2_BIT_HZ|UNDER_LINE_BIT,
            0x1B, '!', H2_BIT_ASCII|W2_BIT_ASCII|UNDER_LINE_BIT,
    };
    private static final byte[] BOILD_MODE_ON        = new byte[]{ 0x1B, 'E' , 1 };
    private static final byte[] BOILD_MODE_OFF       = new byte[]{ 0x1B, 'E' , 0 };

    private static final byte[] REVERSAL_MODE_ON     = new byte[]{ 0x1D, 'B' , 1 };
    private static final byte[] REVERSAL_MODE_OFF    = new byte[]{ 0x1D, 'B' , 0 };

    private static final byte[] UNDER_LINE_HZ        = new byte[]{ 0x1C, '-', 1 };
    private static final byte[] UNDER_LINE2_HZ       = new byte[]{ 0x1C, '-', 2 };
    private static final byte[] CLEAR_UNDER_LINE2_HZ = new byte[]{ 0x1C, '&', 0 };

    private static final byte[] ALGIN_LEFT           = new byte[]{ 0x1B, 'a', 0 };
    private static final byte[] ALGIN_CENTER         = new byte[]{ 0x1B, 'a', 1 };
    private static final byte[] ALGIN_RIGHT          = new byte[]{ 0x1B, 'a', 2 };

    private static final byte[] PRINT_AND_FEED1       = new byte[]{ 0x1B, 'J', 1 };
    private static final byte[] PRINT_AND_FEED2       = new byte[]{ 0x1B, 'J', 2 };
    private static final byte[] PRINT_AND_FEED3       = new byte[]{ 0x1B, 'J', 3 };
    private static final byte[] PRINT_AND_FEED4       = new byte[]{ 0x1B, 'J', 4 };

    private static final byte[] PRINT_AND_FEED_LINE0       = new byte[]{ 0x1B, 'd', 0 };
    private static final byte[] PRINT_AND_FEED_LINE1       = new byte[]{ 0x1B, 'd', 1 };
    private static final byte[] PRINT_AND_FEED_LINE2       = new byte[]{ 0x1B, 'd', 2 };
    private static final byte[] PRINT_AND_FEED_LINE3       = new byte[]{ 0x1B, 'd', 3 };
    private static final byte[] PRINT_AND_FEED_LINE4       = new byte[]{ 0x1B, 'd', 4 };

    private static final byte[] CUT_PAPER                  = new byte[]{ 0x1D, 'V', 48 };
    private static final byte[] CUT_PAPER_HALF             = new byte[]{ 0x1D, 'V', 49 };
    private static final byte[] CUT_PAPER_AND_FEED         = new byte[]{ 0x1D, 'V', 66, 1 };
    private static final byte[] BEEP_OFF                   = new byte[]{ 0x1B, 'C', 1, 1, 0 };

    private static final byte[] SET_ROW_SP                 = new byte[]{ 0x1B, '3', 110 };
    private static final byte[] RESET_ROW_SP               = new byte[]{ 0x1B, '2' };

    private static String TAB1 = "  ";
    private static String TAB3 = "      ";
    private static String TAB7 = "              ";

    private static final Charset gb2312 = Charset.forName("Shift_JIS");

    public static final int PRINTER_OK                       = 0;
    public static final int PRINTER_OFFLINE                  = 1;
    public static final int PRINTER_COVER_OPEN               = 2;
    public static final int PRINTER_PAPER_EMPTY              = 3;
    public static final int PRINTER_BUSY                     = 4;
    public static final int PRINTER_ERROR                    = 255;

    public static final int REPORT_OK                        = 0;
    public static final int REPORT_ERROR                     = 1;


    public int ServerSocketDemo(PrintDto printDto){
        /*int count=0;
        try{
            serverSocket=new ServerSocket(5555);
            System.out.println("START");
            while (true){
                Socket socket=serverSocket.accept();
                count++;
                System.out.println("userlogin:"+count+socket);
                System.out.println(""+socket.getInetAddress().getHostAddress());
                BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"Shift_JIS"));
                String msg=null;
                //System.out.println(br);
                ByteBuffer buf = ByteBuffer.allocate(3072);
                //System.out.println(br.toString());
                String msg2="";
                int i = 0;
                while ((msg = br.readLine())!=null) {
                    msg2=msg2+msg+"\n";
                    msg=msg+"\n";
                    System.out.println(msg);
                    if (i==0) {
                        System.out.println(msg.substring(0, 13));
                        String a1=msg.substring(0,13);
                        buf.put(a1.getBytes());
                        buf.put(msg.getBytes("Shift_JIS"));
                    }
                    else
                        buf.put(msg.getBytes("Shift_JIS"));
                    i++;
                }
                //【1】初期化
                buf.put(INTI_PRINT);
                //【2】文字コード選択
                buf.put(CHAR_SET_HZ);
                //【3】
                buf.put(W2_UNDER_LINE_MODE);
                buf.put(BOILD_MODE_ON);
                buf.put("01卓 4名\n".getBytes("Shift_JIS"));
                buf.put(BOILD_MODE_OFF);
                buf.put(PRINT_AND_FEED_LINE1);
                buf.put(CUT_PAPER_AND_FEED);
                //System.out.println(buf.array());
                //ByteBuffer buf2 = ByteBuffer.wrap(msg2.getBytes());
                Socket sock = null;
                //buf.put(br);
                try {
                    sock = new Socket("192.168.10.241", 9100);
                    OutputStream out = sock.getOutputStream();
                    out.write(buf.array());
                    out.flush();
                    out.close();
                }catch(Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        int result = getPrinterStatus("192.168.10.241", 9100);
        if(result!=PRINTER_OK) {

        }
        ByteBuffer buf = ByteBuffer.allocate(2048);
        //【1】初期化
        buf.put(INTI_PRINT);
        //【2】文字コード選択
        buf.put(CHAR_SET_HZ);
        //【3】
        buf.put(W2_UNDER_LINE_MODE);
        buf.put(BOILD_MODE_ON);
        if(printDto.getItemIs()=="1"){
            buf.put(printDto.getS1().getBytes(gb2312));
            buf.put(W2_MODE);
            if(printDto.getIsSmall()=="1"){
                buf.put(LF);
            }
            buf.put(TAB1.getBytes(gb2312));
            //反転
            buf.put(REVERSAL_MODE_ON);
            String s = " " + printDto.getS2() + " \n";
            buf.put(s.getBytes(gb2312));
            buf.put(REVERSAL_MODE_OFF);
        }else {
            String s = printDto.getS1() + "\n";
            buf.put(s.getBytes(gb2312));
        }
        buf.put(BOILD_MODE_OFF);
        buf.put(NORMAL_MODE);
        buf.put(SET_ROW_SP);
        if(printDto.getItemSet()=="1"){
            buf.put(printDto.getS3().getBytes(gb2312));
            buf.put(LF);
        }
        buf.put(H2_MODE);
        buf.put(printDto.getS4().getBytes(gb2312));
        buf.put(LF);
        buf.put(NORMAL_MODE);
        if(printDto.getItemGet()=="1"){
            buf.put(printDto.getS5().getBytes(gb2312));
            buf.put(LF);
        }
        buf.put(RESET_ROW_SP);
        buf.put(ALGIN_RIGHT);
        buf.put(printDto.getS6().getBytes(gb2312));
        buf.put(LF);
        if(printDto.getNn()!="0"){
            for (int i = 1;i<printDto.getNn().length();i++){
                if(printDto.getNn().substring(i,i+1) == "@"){
                    buf.put(LF);
                }else {
                    buf.put(printDto.getNn().substring(i, i + 1).getBytes(gb2312));
                }
            }
        }
        buf.put(PRINT_AND_FEED_LINE1);
        buf.put(CUT_PAPER_AND_FEED);
        Socket sock = null;
        //buf.put(br);
        try {
            sock = new Socket("192.168.10.241", 9100);
            OutputStream out = sock.getOutputStream();
            out.write(buf.array());
            out.flush();
            result = PRINTER_OK;
            out.close();
        }catch(Exception e) {
            result = PRINTER_ERROR;
        }
        return result;
    }

    private int getPrinterStatus(String ip, int port) {
        int result = PRINTER_OFFLINE;
        Socket sock = null;
        byte[] buf = new byte[512];
        int len = 0;
        try {
            sock = new Socket(ip,port);
            OutputStream out = sock.getOutputStream();
            InputStream input = sock.getInputStream();
            out.write(new byte[]{0x1B,0x76});//send "ESC v"
            out.flush();
            int c = 10000/200;
            while(c-->0 && input.available()<4) {
                try{
                    Thread.sleep(200);
                }catch(InterruptedException ee) {
                }
            }
            if(input.available()>0) {
                len = input.read(buf, 0, input.available());
            }
            out.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sock!=null && !sock.isClosed()) {
                try {
                    sock.close();
                } catch (Exception e) {
                }
            }
        }
        if(len>3){
            if((buf[0]&0x00000008)!=0) {
                result = PRINTER_OFFLINE;
            } else if((buf[0]&0x00000020)!=0) {
                result = PRINTER_COVER_OPEN;
            } else if((buf[1]&0x00000060)!=0) {
                result = PRINTER_ERROR;
            } else if((buf[2]&0x0000000C)!=0) {
                result = PRINTER_PAPER_EMPTY;
            } else {
                result = PRINTER_OK;
            }
        }
        return result;
    }
}
