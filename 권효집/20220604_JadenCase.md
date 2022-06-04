## JadenCase 문자열 만들기

https://programmers.co.kr/learn/courses/30/lessons/12951

```java
class Solution {
  public String solution(String s) {

    return Pattern.compile("\\w+\\b\\s*")
      .matcher(s).results().parallel()
      .map(result -> {
        String text = result.group();
        char firstLetter = text.charAt(0);
        return Character.toUpperCase(firstLetter) + text.substring(1).toLowerCase();
      }).collect(Collectors.joining(""));
  }
}
```

- 다르게 풀고 싶어서 병렬처리해봄