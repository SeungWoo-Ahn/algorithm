import java.util.*;

class Solution {
    static class Page {
        final int id;
        final String url;
        final int basicScore;
        final List<String> links;
        private double linkScore;
        
        public Page(int id, String url, int basicScore, List<String> links) {
            this.id = id;
            this.url = url;
            this.basicScore = basicScore;
            this.links = links;
            this.linkScore = 0.0;
        }
        
        public double calcLinkScore() {
            if (links.size() == 0) {
                return 0.0;
            }
            return ((double) basicScore) / links.size();
        }
        
        public void addLinkScore(Page o) {
            linkScore += o.calcLinkScore();
        }
        
        public double matchingScore() {
            return basicScore + linkScore;
        }
    }
    
    static class Tag {
        final String name;
        final boolean close;
        final Map<String, String> attrs;
        
        public Tag(String name, boolean close, Map<String, String> attrs) {
            this.name = name;
            this.close = close;
            this.attrs = attrs;
        }
    }
    
    private Page parse(int id, String target, String page) {
        int n = page.length();
        String url = null;
        int basicScore = 0;
        List<String> links = new ArrayList<>();
        boolean inHead = false;
        int idx = 0;
        while (idx < n) {
            if (page.charAt(idx) != '<') { // 태그 밖: 단어
                while (idx < n && page.charAt(idx) != '<') {
                    if (!isAlpha(page.charAt(idx))) {
                        idx++;
                        continue;
                    }
                    int st = idx;
                    while (idx < n && isAlpha(page.charAt(idx))) {
                        idx++;
                    }
                    String word = page.substring(st, idx);
                    if (word.toUpperCase().equals(target)) {
                        basicScore++;
                    }
                }
                continue;
            }
            // 태그 안: 태그
            int en = idx + 1;
            while (en < n && page.charAt(en) != '>') {
                en++;
            }
            String s = page.substring(idx + 1, en);
            Tag tag = getTag(s);
            switch (tag.name) {
                case "head":
                    inHead = !tag.close;
                    break;
                case "meta":
                    if (inHead &&
                        tag.attrs.containsKey("property") && 
                        tag.attrs.get("property").equals("og:url") &&
                        tag.attrs.containsKey("content") &&
                        tag.attrs.get("content").startsWith("https://")) {
                        url = tag.attrs.get("content");
                    }
                    break;
                case "a":
                    if (!tag.close &&
                        tag.attrs.containsKey("href") &&
                        tag.attrs.get("href").startsWith("https://")) {
                        links.add(tag.attrs.get("href"));
                    }
            }
            idx = en + 1;
        }
        if (url == null) {
            throw new IllegalStateException();
        }
        return new Page(id, url, basicScore, links);
    }
    
    private boolean isAlpha(char ch) {
        return ('a' <= ch && ch <= 'z') ||
            ('A' <= ch && ch <= 'Z');
    }
    
    private Tag getTag(String s) {
        if (s.charAt(0) == '/') {
            String name = s.substring(1);
            return new Tag(name, true, null);
        }
        int n = s.length();
        boolean close = s.charAt(n - 1) == '/';
        if (close) {
            s = s.substring(0, n - 1);
        }
        Map<String, String> attrs = new HashMap<>();
        String[] sp = s.split(" ");
        String name = sp[0];
        for (int i = 1; i < sp.length; i++) {
            String attr = sp[i];
            int equalIdx = attr.indexOf('=');
            if (equalIdx < 0) continue;
            String attrName = attr.substring(0, equalIdx);
            String attrContent = attr.substring(equalIdx + 2, attr.length() - 1);
            attrs.put(attrName, attrContent);
        }
        return new Tag(name, close, attrs);
    }
    
    // 기본 점수: 텍스트 중 검색어가 등장한 횟수(대소문자 무시)
    // 외부 링크 수: 다른 외부 페이지로 연결된 링크 개수
    // 링크 점수: 해당 웹페이지의 링크가 걸린 다른 웹페이지의 (기본점수 / 외부 링크 수)의 총합
    // url: head > meta > property=og:url
    // 외부 링크: a > href
    // 검색어: 단어 단위로 비교, 단어: 알파벳을 제외한 모든 문자로 구분
    // 1. 기본 점수, 외부 링크 수를 각각 구한다
    // 2. 링크 점수를 더해준다
    // 매칭 점수가 가장 높은 페이지의 index를 구해라 (여러 개면 번호가 가장 작은 것)
    public int solution(String word, String[] pages) {
        String target = word.toUpperCase();
        Map<String, Page> map = new HashMap<>();
        for (int id = 0; id < pages.length; id++) {
            Page page = parse(id, target, pages[id]);
            map.put(page.url, page);
        }
        for (Page page : map.values()) {
            for (String link : page.links) {
                if (map.containsKey(link)) {
                    map.get(link).addLinkScore(page);
                }
            }
        }
        double maxScore = -1.0;
        int maxId = pages.length;
        for (Page page : map.values()) {
            double matchingScore = page.matchingScore();
            if (matchingScore > maxScore) {
                maxScore = matchingScore;
                maxId = page.id;
            } else if (matchingScore == maxScore && page.id < maxId) {
                maxId = page.id;
            }
        }
        return maxId;
    }
}