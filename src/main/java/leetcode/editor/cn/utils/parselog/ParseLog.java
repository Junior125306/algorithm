package leetcode.editor.cn.utils.parselog;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParseLog {

    public List<List<Object>> main() {
        List<List<Object>> list = null;
        try {
            // 打开结果文件
            String output = "C:\\Users\\Junior\\Desktop\\out19.txt";
            String fold = "C:\\Users\\Junior\\Desktop\\revenue_log.log.2021-04-28";
            String key = "=============dxxx";
            FileWriter writer = new FileWriter(output);
            BufferedWriter bw = new BufferedWriter(writer);
            //参数0为目录名，对目录中的所有文件进行循环处理
            File file = new File(fold);
            list = processOneFile(file, key, bw);
            bw.flush();
            writer.flush();
            bw.close();
            writer.close();
            System.out.println("处理End：");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    //判断source中是否含有关键字keys，关键字按逗号分割。如果source中包含
    //给定的所有关键字，返回真。否则，返回false。
    public static boolean contains(String source, String keys) {
        for (String key : keys.split(",")) {
            //只要有不包含的，就返回false
            if (!source.contains(key)) {
                return false;
            }
        }
        return true;
    }

    //处理一个日志文件，从日志文件里找到含所有关键字的行，写到目标文件中。
    public static List<List<Object>> processOneFile(File logFile, String keys, BufferedWriter bw) {
        List<List<Object>> list = null;
        try {
            list = new ArrayList<List<Object>>();
            // 打开日志文件以及结果文件 "UTF-8"或者"GB2312"
            InputStreamReader reader = new InputStreamReader(new FileInputStream(logFile), "GB2312");
            BufferedReader br = new BufferedReader(reader);

            // 从日志文件里读一行
            int i = 0;
            int a = 1;
            String str = br.readLine();
            while (str != null) {
                // 如果包括报建名，对这一行格式化后存入结果文件中
                if (contains(str, keys)) {
                    str = str.substring(str.indexOf("{"));
                    JSONObject jsonObject = JSONObject.parseObject(str);
                    if (a >= 256) {
                        List<Object> data = new ArrayList<Object>();
                        data.add(jsonObject.getJSONObject("userinfo").get("f_user_name"));
                        list.add(data);
                    }
                    bw.append(str).append("\n");
                    bw.newLine();
                    a++;
                }
                str = br.readLine();
                i++;
            }
            br.close();
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }


    @Test
    public void noModelWrite() {
        // 写法1
        String fileName = "C:\\Users\\Junior\\Desktop\\2.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("Sheet1").doWrite(main());
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head = new ArrayList<String>();
        head.add("你好");
        list.add(head);
        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            list.add(data);
        }
        return list;
    }
}
