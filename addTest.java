import java.net.*;
import java.io.*;
import net.sf.json.*;

public class addTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://172.18.94.225:10002/api/Task");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            JSONObject task = new JSONObject();

            task.put("$class", "com.smie.test.Task");
            task.put("tid", "t1");
            task.put("title", "123");
            task.put("todo", "qqwrv");
            task.put("start", "2018-11-01T07:03:20.819Z");
            task.put("ddl", "2018-11-02T07:03:20.819Z");
            task.put("owner", new JSONObject());

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
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}