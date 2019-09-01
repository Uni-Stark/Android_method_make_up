import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class onHTTPConnection {

    String EXCEPTION_ERROR = "Exception Occured. Check the url";


    public String POSTFunction(String mUrl, String params) {

        try {
            //�޾ƿ� String�� url�� ������ֱ�
            URL url = new URL(mUrl);

            //conn���� url connection�� open ���ֱ�
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //�ҷ����µ� �ð��� ���� �ɸ��� ��� Time out ����
            conn.setReadTimeout(10000);
            //�����ϴµ� �ð��� ���� �ɸ��� ��� Time out ����
            conn.setConnectTimeout(15000);
            //���� ��� ����
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //Accept-Charset ���� UTF-8 or ASCII
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");

            // POST�� �Ѱ��� �Ķ���� ����.
            byte[] outputInBytes = params.getBytes(StandardCharsets.UTF_8);
            OutputStream os = conn.getOutputStream();
            os.write(outputInBytes);
            os.close();

            //������� �޾ƿ´�.
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            br.close();

            String res = response.toString();
            res = res.trim();

            return res;

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("ERROR", EXCEPTION_ERROR);
        return null;
    }

    public String GETFunction(String mUrl) {
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            InputStream is = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String result;
            while ((result = br.readLine()) != null) {
                sb.append(result + '\n');
            }

            result = sb.toString();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("ERROR", EXCEPTION_ERROR);
        return null;
    }
}
