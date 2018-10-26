import java.net.HttpURLConnection;
import java.net.URL;

public class addTest {
    public static void main(String[] args) {
        URL url = new URL("172.18.94.225:10002");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true); 
        connection.setRequestMethod("POST"); 
        connection.setUseCaches(false); 
        connection.setInstanceFollowRedirects(true); 
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
        connection.connect(); 
        DataOutputStream out = new DataOutputStream(connection.getOutputStream()); 
        JSONObject task = new JSONObject(); 
        task.put("tid", "t1"); 
        task.put("title", "123");
        task.put("todo", "qqwrv");
        task.put("start", "2018/10/26");
        task.put("ddl", "2018/10/27");
        task.put("owner", "null");

        out.write(task.toString().getBytes("UTF-8"));
        out.flush(); 
        out.close(); 

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
        String lines; 
        StringBuffer sb = new StringBuffer(""); 
        while ((lines = reader.readLine()) != null) { 
            lines = new String(lines.getBytes(), "utf-8"); 
            sb.append(lines); 
        } 
        System.out.println(sb); 
        reader.close(); 
        // 断开连接 
        connection.disconnect(); 
    }
}