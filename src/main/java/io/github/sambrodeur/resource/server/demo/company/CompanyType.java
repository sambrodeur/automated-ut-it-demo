package io.github.sambrodeur.resource.server.demo.company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CompanyType {
  FOOD(1),
  SERVICES(2),
  HARDWARE(3),
  HEALTH(4);

  private final int key;

  public static CompanyType getBy(int key) {
    return Arrays.stream(values())
      .filter(type -> type.key == key)
      .findFirst()
      .orElse(null);
  }
}
