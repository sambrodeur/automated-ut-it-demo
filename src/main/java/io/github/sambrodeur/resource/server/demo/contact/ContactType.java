package io.github.sambrodeur.resource.server.demo.contact;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ContactType {
  EMAIL(1),
  PHONE(2),
  MOBILE(3);

  private final int key;

  public static ContactType fromKey(int key) {
    return Arrays.stream(values())
      .filter(x -> x.key == key)
      .findFirst()
      .orElse(null);
  }

}
