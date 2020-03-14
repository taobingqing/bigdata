package com.post_rest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private CloseableHttpClient httpClient;
    public HttpClientUtils() {
        // 1 创建HttpClinet，相当于打开浏览器
        httpClient = HttpClients.createDefault();
    }

    public static void main(String[] args) throws IOException {
        new HttpClientUtils().httpPostTest();
    }

    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map不为空
        if (map != null) {
            // 声明存放参数的List集合
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // 遍历map，设置参数到list中
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 创建form表单对象
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

            // 把表单对象设置到httpPost中
            httpPost.setEntity(formEntity);
        }

        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            HttpResult httpResult = new HttpResult();
            httpResult.setCode(404);
            httpResult.setBody("请求失败");
            return httpResult;
        }

        // 解析response封装返回对象httpResult
        HttpResult httpResult = new HttpResult();
        // 解析数据封装HttpResult
        if (response.getEntity() != null) {
            //httpResult = new HttpResult(response.getStatusLine().getStatusCode(),EntityUtils.toString(response.getEntity(),"UTF-8"));
            httpResult.setCode(response.getStatusLine().getStatusCode());
            httpResult.setBody(EntityUtils.toString(response.getEntity(),"UTF-8"));

        } else {
            //httpResult = new HttpResult(response.getStatusLine().getStatusCode(), "");
            httpResult.setCode(response.getStatusLine().getStatusCode());
            //httpResult.setBody("");
        }

        // 返回结果
        return httpResult;
    }

    public void httpPostTest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://10.11.58.44:8083/query/impala");

//        StringEntity input = new StringEntity("{" +
//                "\"executeTime\": \"httpclienttest\"," +
//                "\"failReason\": \"httpclienttest\"," +
//                "\"fileName\": \"httpclienttest\"," +
//                "\"finishTime\": \"httpclienttest\"," +
//                "\"ftpPath\": \"httpclienttest\"," +
//                "\"id\": 0," +
//                "\"jobName\": \"httpclienttest\"," +
//                "\"sucessFlag\": 0" +
//                "}"); //here instead of JSON you can also have XML

        StringEntity input = new StringEntity("{" +
                "\"backendType\": 2," +
                "\"clusterName\": \"临港集群\"," +
                "\"creator\": \"\"," +
                "\"ctlStatus\": 0," +
                "\"description\": \"\"," +
                "\"executeSql\": \"select * from ipms.dw_dm_ba_bb_residential_area  limit 2;\"," +
                "\"ftpPath\": \"/home/dxwh/ziyan_test/\"," +
                "\"id\": \"0002\"," +
                "\"jobName\": \"test0002\"," +
                "\"schedulePolicy\": \"\"," +
                "\"secAuditFlag\": 0," +
                "\"secAuditor\": \"\"," +
                "\"secSuggestion\": \"\"," +
                "\"sqlAuditFlag\": 0," +
                "\"sqlAuditor\": \"\"," +
                "\"sqlSuggestion\": \"\"" +
                "}");
        input.setContentType("application/json");
        input.setContentEncoding("utf-8");
        httpPost.setEntity(input);
        CloseableHttpResponse response = client.execute(httpPost);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
}
