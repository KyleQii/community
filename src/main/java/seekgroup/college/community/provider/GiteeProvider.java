package seekgroup.college.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import seekgroup.college.community.dto.AccessTokenDTO;
import seekgroup.college.community.dto.GiteeUser;

import java.io.IOException;

@Slf4j
//数据传输模型
@Component
public class GiteeProvider {

    @Value("${gitee.client.id}")
    private String clientId;

    @Value("${gitee.client.secret}")
    private String clientSecret;

    @Value("${gitee.redirect.uri}")
    private String redirectUri;

    //将传入的数据以json的形式全部序列化到body里并以post的方式发送给服务器
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        String url = "https://gitee.com/oauth/token?grant_type=authorization_code&code=%s&client_id=%s&redirect_uri=%s&client_secret=%s";
        url = String.format(url, accessTokenDTO.getCode(), clientId, redirectUri, clientSecret);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject json = JSON.parseObject(string);
            string = (String) json.get("access_token");
            log.info("gitee response :{}", string);
            return string;
        } catch (IOException e) {
            log.error("getAccessToken error,{}", accessTokenDTO, e);
        }
        return null;
    }
    //获取用户数据
    public GiteeUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GiteeUser giteeUser = JSON.parseObject(string, GiteeUser.class);

            return giteeUser;
        } catch (IOException e) {
            log.error("gitUser error,{}", accessToken, e);
        }
        return null;
    }

}
