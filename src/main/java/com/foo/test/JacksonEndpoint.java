package com.foo.test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
@Endpoint(id = "jackson", enableByDefault = true)
public class JacksonEndpoint {

  @Autowired
  private ObjectMapper objectMapper;

  @ReadOperation
  public Map<String, Object> read() {
    final SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
    final DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig();
    final Map<String, Object> result = new TreeMap<>();
    result.put("serialization", Stream.of(SerializationFeature.values()).collect(Collectors.toMap(SerializationFeature::name, serializationConfig::isEnabled)));
    result.put("deserialization", Stream.of(DeserializationFeature.values()).collect(Collectors.toMap(DeserializationFeature::name, deserializationConfig::isEnabled)));
    Map<String, Object> dates = Map.of(
        "java.time.Instant", Instant.now(),
        "java.time.ZonedDateTime", ZonedDateTime.now(),
        "java.time.LocalDateTime", LocalDateTime.now(),
        "java.time.Duration", Duration.ofMinutes(5),
        "java.util.date.Date", new Date()
        );
    result.put("dates", new TreeMap<>(dates));
    return result;
  }
}
