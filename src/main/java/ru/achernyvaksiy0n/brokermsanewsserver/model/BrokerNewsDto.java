package ru.achernyvaksiy0n.brokermsanewsserver.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;

/** @author Anton Chernyavskiy (agchernyavskii@vtb.ru) */
@Data
@Builder
public class BrokerNewsDto {

  private UUID uuid;

  @DateTimeFormat(
      iso = DateTimeFormat.ISO.DATE,
      fallbackPatterns = {"yyy/MM/dd HH:mm:ss"})
  private String dateTime;

  private String description;
}
