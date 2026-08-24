import java.util.*;

class Solution {
    static class Genre implements Comparable<Genre> {
        private final List<Song> songs;
        private int playAcc;
        
        public Genre() {
            this.songs = new ArrayList<>();
            this.playAcc = 0;
        }
        
        public void add(Song song) {
            songs.add(song);
            playAcc += song.play;
        }
        
        public List<Integer> top2SongIds() {
            Collections.sort(songs);
            List<Integer> ids = new ArrayList<>();
            for (int i = 0; i < Math.min(songs.size(), 2); i++) {
                ids.add(songs.get(i).id);
            }
            return ids;
        }
        
        @Override
        public int compareTo(Genre o) {
            return o.playAcc - playAcc;
        }
    }
    
    static class Song implements Comparable<Song> {
        final int id;
        final int play;
        
        public Song(int id, int play) {
            this.id = id;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song o) {
            if (play != o.play) {
                return o.play - play;
            }
            return id - o.id;
        }
    }
    
    public List<Integer> solution(String[] genres, int[] plays) {
        Map<String, Genre> map = new HashMap<>();
        for (int id = 0; id < genres.length; id++) {
            String key = genres[id];
            Genre genre = map.getOrDefault(key, new Genre());
            genre.add(new Song(id, plays[id]));
            map.put(key, genre);
        }
        List<Genre> gs = new ArrayList<>();
        for (String key : map.keySet()) {
            gs.add(map.get(key));
        }
        Collections.sort(gs);
        List<Integer> result = new ArrayList<>();
        for (Genre genre : gs) {
            result.addAll(genre.top2SongIds());
        }
        return result;
    }
}