package org.example.leetcode.problems._9_contest.week317;

import java.util.*;

//6221. 最流行的视频创作者
public class MostPopularCreator {
    public static void main(String[] args) {
        MostPopularCreator mostPopularCreator = new MostPopularCreator();
        System.out.println(mostPopularCreator.mostPopularCreator(new String[]{"a", "a", "a", "b"},
                new String[]{"1", "2", "3", "4"},
                new int[]{10_0000_0000, 10_0000_0000, 10_0000_0000, 1}));
    }

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        ArrayList<List<String>> ans = new ArrayList<>();

        //遍历，以「创作者」为键，「播放量最大的视频id」和「总播放量」为联合值。
        HashMap<String, IdCnt> person2cnt = new HashMap<>();
        for (int i = 0; i < creators.length; i++) {
            if (!person2cnt.containsKey(creators[i])) {
                person2cnt.put(creators[i], new IdCnt(i, views[i]));
            } else {
                IdCnt idCnt = person2cnt.get(creators[i]);
                if (views[i] > views[idCnt.id]||
                        (views[i] == views[idCnt.id] && ids[i].compareTo(ids[idCnt.id]) < 0))
                    idCnt.id = i;
                idCnt.cnt = idCnt.cnt + views[i];
            }
        }

        //遍历哈希映射，找出总播放量的键值对，加入结果集合
        long max = -1;
        for (Map.Entry<String, IdCnt> entry : person2cnt.entrySet()) {
            IdCnt idCnt = entry.getValue();
            if (idCnt.cnt == max) {
                ans.add(List.of(entry.getKey(), ids[idCnt.id]));
            } else if (idCnt.cnt > max) {
                max = idCnt.cnt;
                ans.clear();
                ans.add(List.of(entry.getKey(), ids[idCnt.id]));
            }
        }

        return ans;
    }

    class IdCnt {
        public int id;
        public long cnt;

        public IdCnt(int id, long cnt) {
            this.id = id;
            this.cnt = cnt;
        }
    }
}
