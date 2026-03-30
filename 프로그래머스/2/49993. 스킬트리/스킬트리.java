import java.util.Set;
import java.util.HashSet;

class Solution {
    private final Set<Character> skillSet = new HashSet<>();
    
    private void setSkillSet(String skill) {
        for (int i = 0; i < skill.length(); i++) {
            skillSet.add(skill.charAt(i));
        }
    }
    
    private boolean isCorrect(String skill, String skillTree) {
        int cur = 0;
        for (int i = 0; i < skillTree.length(); i++) {
            char ch = skillTree.charAt(i);
            if (!skillSet.contains(ch)) continue;
            if (skill.charAt(cur) != ch) return false;
            cur++;
        }
        return true;
    }
    
    public int solution(String skill, String[] skill_trees) {
        setSkillSet(skill);
        int result = 0;
        for (String skillTree : skill_trees) {
            if (isCorrect(skill, skillTree)) {
                result++;
            }
        }
        return result;
    }
}