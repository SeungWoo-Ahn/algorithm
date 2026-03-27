import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

class Solution {
    private static class Genre implements Comparable<Genre> {
        private final List<Music> musics;
        private int accPlayCnt;
        
        public Genre() {
            this.musics = new ArrayList<>();
            this.accPlayCnt = 0;
        }
        
        public void add(int id, int playCnt) {
            musics.add(new Music(id, playCnt));
            accPlayCnt += playCnt;
        }
        
        public List<Music> getMusics() {
            Collections.sort(musics);
            return musics;
        }
        
        @Override
        public int compareTo(Genre o) {
            return o.accPlayCnt - accPlayCnt;
        }
    }
    
    private static class Music implements Comparable<Music> {
        final int id;
        final int playCnt;
        
        public Music(int id, int playCnt) {
            this.id = id;
            this.playCnt = playCnt;
        }
        
        @Override
        public int compareTo(Music o) {
            if (playCnt != o.playCnt) {
                return o.playCnt - playCnt;
            }
            return id - o.id;
        }
    }
    
    public List<Integer> solution(String[] genres, int[] plays) {
        Map<String, Genre> genreMap = new HashMap<>();
        for (int id = 0; id < genres.length; id++) {
            Genre genre = genreMap.getOrDefault(genres[id], new Genre());
            genre.add(id, plays[id]);
            genreMap.put(genres[id], genre);
        }
        List<Genre> gs = new ArrayList<>();
        for (String key : genreMap.keySet()) {
            Genre genre = genreMap.get(key);
            gs.add(genreMap.get(key));
        }
        Collections.sort(gs);
        List<Integer> result = new ArrayList<>();
        for (Genre genre : gs) {
            int insertCnt = 0;
            for (Music music : genre.getMusics()) {
                result.add(music.id);
                insertCnt++;
                if (insertCnt == 2) break;
            }
        }
        return result;
    }
}