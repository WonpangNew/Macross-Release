package com.jlu.release.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by niuwanpeng on 17/4/7.
 */
public class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 返回一个httpClient对象
     * @return
     */
    public static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        return httpClient;
    }

    public static String get(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = getHttpClient();
        String returnContent = "";
        try {
            StringBuilder sb = new StringBuilder(url);
            if(null != params && params.size() > 0){
                sb.append("?");
                for(Map.Entry<String, String> entry :params.entrySet()){
                    sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            url = sb.toString();
            if(null!=url){
                url = url.replaceAll(" ", "%20");
            }
            LOGGER.info("[HTTP GET]:{} starting...", url);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    returnContent = EntityUtils.toString(entity, HTTP.UTF_8);
                }
            }else {
                LOGGER.error("Failed to get URL: " + url + " with response: " + response.getStatusLine().getReasonPhrase());
            }
            LOGGER.info("[HTTP GET]:{} end!!!", url);
        } catch (IOException e) {
            LOGGER.error("Exception happened when request the url:"+url,e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnContent;
    }
}
