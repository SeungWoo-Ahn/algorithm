import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

class Solution {
    private static class File implements Comparable<File> {
        final int id;
        final String head;
        final String number;
        final String tail;
        
        public File(int id, String fileName) {
            this.id = id;
            int[] en = new int[2];
            int i = 0;
            while (i < fileName.length() && !isNumber(fileName.charAt(i))) {
                i++;
            }
            en[0] = i;
            while (i < fileName.length() && isNumber(fileName.charAt(i))) {
                i++;
            }
            en[1] = i;
            this.head = fileName.substring(0, en[0]);
            this.number = fileName.substring(en[0], en[1]);
            this.tail = fileName.substring(en[1]);
        }
        
        private boolean isNumber(char ch) {
            return '0' <= ch && ch <= '9';
        }
        
        @Override
        public int compareTo(File o) {
            String h1 = head.toUpperCase();
            String h2 = o.head.toUpperCase();
            if (!h1.equals(h2)) {
                return h1.compareTo(h2);
            }
            int n1 = Integer.parseInt(number);
            int n2 = Integer.parseInt(o.number);
            if (n1 != n2) {
                return n1 - n2;
            }
            return id - o.id;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(head);
            sb.append(number);
            sb.append(tail);
            return sb.toString();
        }
    }
    
    public List<String> solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        for (int id = 0; id < files.length; id++) {
            fileList.add(new File(id, files[id]));
        }
        Collections.sort(fileList);
        return fileList.stream()
            .map(File::toString)
            .collect(Collectors.toList());
    }
}