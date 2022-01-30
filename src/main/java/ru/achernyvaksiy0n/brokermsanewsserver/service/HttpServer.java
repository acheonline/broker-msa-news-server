package ru.achernyvaksiy0n.brokermsanewsserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.achernyvaksiy0n.brokermsanewsserver.model.BrokerNewsDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/** @author Anton Chernyavskiy (agchernyavskii@vtb.ru) */
@Service
@Getter
@Setter
@Slf4j
@EnableAsync
public class HttpServer {

  public static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

  @Value("${output.host}")
  private String host;

  @Async
  @Scheduled(fixedDelayString = "${output.delay}")
  public void scheduledPostRequester() throws IOException {
    try {
      CloseableHttpClient httpclient = HttpClients.createDefault();
      HttpPost httpPost = new HttpPost(host);
      final UUID uuid = UUID.randomUUID();
      BrokerNewsDto news =
          BrokerNewsDto.builder()
              .uuid(uuid)
              .dateTime(LocalDateTime.now().format(DATE_TIME_FORMATTER))
              .description("Dummy description with dummy uuid = ".concat(uuid.toString()))
              .build();

      String request = new ObjectMapper().writer().writeValueAsString(news);

      HttpEntity stringEntity = new StringEntity(request, ContentType.APPLICATION_JSON);
      httpPost.setEntity(stringEntity);

      CloseableHttpResponse response = httpclient.execute(httpPost);
      log.info(
          "Message {} sent at host: {}. RESPONSE code status: {}",
          request,
          host,
          response.getStatusLine().getStatusCode());
      httpclient.close();
    } catch (HttpHostConnectException e) {
      log.error("RECEIVER is unavailable now at: {}. Connection and post request failed", host);
    }
  }
}
