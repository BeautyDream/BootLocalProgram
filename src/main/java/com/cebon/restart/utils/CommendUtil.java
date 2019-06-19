package com.cebon.restart.utils;

import java.io.*;

/**
 * @Auther: LiaoPeng
 * @Date: 2019/5/31
 */
public class CommendUtil {

    public static String exec(String cmd){
        if(cmd==null||cmd.isEmpty()) return null;
        try {
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec(cmd);

            InputStream input = exec.getInputStream();
            OutputStream output = exec.getOutputStream();
            InputStream error = exec.getErrorStream();

            String inputInfo = noThreadReadStreamInfo(input,"inputStreamInfo");
            readStreamInfo(error,"ErrorStreamInfo");



            output.close();
            int i = exec.waitFor();
            exec.destroy();
            if(i==0){
                System.out.println("子进程正常完成");
            }else{
                System.err.println("子进程异常结束");
            }

            System.out.println("inputInfo = "+inputInfo);
            if(inputInfo.isEmpty()){
                return null;
            }else{
                return inputInfo;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static void readStreamInfo(InputStream in,String type){
        if (in == null) {
            return;
        }
        new Thread(()->{
            BufferedReader br = null;
            try{
                br = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while((line = br.readLine())!=null){
                    System.out.println(type +": "+line);
                }

            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    private static String noThreadReadStreamInfo(InputStream in,String type){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try{
            br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = br.readLine())!=null){
                sb.append(line);
                System.out.println(type +": "+line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().trim();
    }


}
