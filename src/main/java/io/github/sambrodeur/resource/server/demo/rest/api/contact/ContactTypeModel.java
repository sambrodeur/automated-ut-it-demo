package io.github.sambrodeur.resource.server.demo.rest.api.contact;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ContactTypeModel {
  EMAIL(1),
  PHONE(2),
  MOBILE(3);

  @JsonValue
  private final int key;

  @JsonCreator
  public static ContactTypeModel fromKey(int key) {
    return Arrays.stream(values())
      .filter(x -> x.key == key)
      .findFirst()
      .orElse(null);
  }
}
