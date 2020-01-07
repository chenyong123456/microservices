package cn.knowimage.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.List;

/**
 * 定义响应结构的结果自定义
 */
public class ClincialResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static ClincialResult build(Integer status, String msg, Object data) {
        return new ClincialResult(status, msg, data);
    }

    public static ClincialResult ok(Object data) {

        return new ClincialResult(data);
    }

    public static ClincialResult ok() {

        return new ClincialResult(null);
    }

    public ClincialResult() {

    }

    public static ClincialResult build(Integer status, String msg) {
        return new ClincialResult(status, msg, null);
    }

    public ClincialResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ClincialResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为ClincialResult对象
     *
     * @param jsonData json数据
     * @param clazz    ClincialResult中的object类型
     * @return
     */
    public static ClincialResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ClincialResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ClincialResult format(String json) {
        try {
            return MAPPER.readValue(json, ClincialResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static ClincialResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect("192.168.50.4", 21);// 连接FTP服务器
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            //ftp.connect(host);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login("ftpuser", "ftpuser");// 登录

            reply = ftp.getReplyCode();
            System.out.println("连接图片服务器服务器响应编号:--->" + reply);
             System.out.println("查看连接服务器的状态:--->" + FTPReply.isPositiveCompletion(reply));
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            //在这里进行图片的上传
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\wh123\\Desktop\\QQ截图20191114164247.png"));
           // ftp.changeWorkingDirectory("/usr/local/nginx/html/image");
            ftp.changeWorkingDirectory("/usr/local/nginx/html/image");
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.storeFile("hello1.png", inputStream);
            inputStream.close();
            ftp.logout();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
