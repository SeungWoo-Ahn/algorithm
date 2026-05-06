import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {    
    public List<String> solution(String[] record) {
        Map<String, String> nicknameMap = new HashMap<>();
        for (String r : record) {
            String[] sp = r.split(" ");
            if (sp[0].equals("Leave")) continue;
            String uid = sp[1];
            String nickname = sp[2];
            nicknameMap.put(uid, nickname);
        }
        List<String> result = new ArrayList<>();
        for (String r : record) {
            String[] sp = r.split(" ");
            String op = sp[0];
            if (op.equals("Change")) continue;
            String nickname = nicknameMap.get(sp[1]);
            String message = nickname + "님이 ";
            if (op.equals("Enter")) {
                message += "들어왔습니다.";
            } else if (op.equals("Leave")) {
                message += "나갔습니다.";
            }
            result.add(message);
        }
        return result;
    }
}