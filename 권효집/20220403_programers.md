### 프로그래머스 2022 KAKAO BLIND RECRUITMENT
#### 신고 결과 받기

https://programmers.co.kr/learn/courses/30/lessons/92334

```import java.util.*;
import java.util.stream.Collectors;

class Solution {

  public int[] solution(String[] idList, String[] reports, int life) {

    Map<String, User> memoryDB = Arrays.stream(idList)
            .collect(Collectors.toMap(
                    id -> id,
                    id -> new User(id, life),
                    (x,y) -> y,
                    LinkedHashMap::new));

    for (String report : reports) {
      String[] text = report.split(" ");
      User reporter = memoryDB.get(text[0]);
      User target = memoryDB.get(text[1]);

      reporter.report(target);
    }

    Set<User> blackUsers = memoryDB.values().stream()
            .filter(user -> user.life <= 0)
            .collect(Collectors.toSet());

    return memoryDB.values().stream()
            .mapToInt(user -> {
              user.notifyBlackedUser(blackUsers);
              return user.mailboxCount;
            }).toArray();
  }
}

class User {
  String id;
  int life;
  int mailboxCount;
  HashSet<User> blackList;

  public User(String id, int life) {
    this.id = id;
    this.life = life;
    this.mailboxCount = 0;
    this.blackList = new HashSet<>();
  }

  public void report(User target) {
    if (blackList.contains(target)) return;
    target.life--;
    blackList.add(target);
  }

  public void notifyBlackedUser(Set<User> users) {
    int before = blackList.size();
    blackList.removeAll(users);
    int after = blackList.size();
    mailboxCount = before - after;
  }
}
