package io.github.sambrodeur.resource.server.demo.rest.api.company;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CompanyTypeModel {
  FOOD(1),
  SERVICES(2),
  HARDWARE(3),
  HEALTH(4);

  @JsonValue
  private final int key;

  @JsonCreator
  public static CompanyTypeModel getBy(int key) {
    return Arrays.stream(values())
      .filter(type -> type.key == key)
      .findFirst()
      .orElse(null);
  }
}
