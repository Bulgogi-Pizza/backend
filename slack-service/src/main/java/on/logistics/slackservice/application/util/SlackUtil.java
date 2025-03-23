package on.logistics.slackservice.application.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.logistics.slackservice.application.dtos.SlackMessageRequestDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackUtil {

    @Value("${slack.bot.token}")
    private String slackToken;

    public String getSlackIdByEmail(String email) {
        String url = "https://slack.com/api/users.lookupByEmail?email=" + email;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + slackToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            requestEntity,
            String.class
        );
        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        JSONObject profile = jsonObject.getJSONObject("user");
        return (String) profile.get("id");
    }

    public void sendMessageToUser(SlackMessageRequestDto requestDto) {
        String url = "https://slack.com/api/chat.postMessage";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + slackToken);
        headers.add("Content-Type", "application/json; charset=utf-8");

        String slackId = getSlackIdByEmail(requestDto.slackReceiveEmail());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channel", slackId);
        jsonObject.put("text", requestDto.message());
        String body = jsonObject.toString();

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            String.class
        );

        HttpStatusCode httpStatus = responseEntity.getStatusCode();
        log.info("슬랙 메세지 전송 응답, httpStatus: {}", httpStatus);
    }

}
