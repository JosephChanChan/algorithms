package tables;

import java.util.*;


/**
 * lc 355 medium
 *
 * Analysis:
 * n是用户数
 * 时间复杂度：getNewsFeed O(n) 其余O(1)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/7/16
 */
public class DesignTweet {

    // 全局时间，记录发推特的顺序，推特id居然不是递增的
    int seq = 0;

    // 用户推特链表 userId -> 推特链表
    Map<Integer, ArrayDeque<int[]>> userTweets = new HashMap<>();

    // 用户关注池 userId -> 关注的用户id集合
    Map<Integer, HashSet<Integer>> userFollowees = new HashMap<>();


    public void postTweet(int userId, int tweetId) {
        ArrayDeque<int[]> q = userTweets.getOrDefault(userId, new ArrayDeque<>(10));
        if (q.size() == 10) {
            q.removeLast();
        }
        q.addFirst(new int[]{tweetId, seq++});
        userTweets.put(userId, q);
        //System.out.println("userId="+userId+" post="+tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<ArrayDeque> ques = new LinkedList<>();

        // 拉取用户关注的所有用户的tweets的top10，然后多路归并取top10
        HashSet<Integer> followees = userFollowees.get(userId);
        if (null != followees) {
            for (Integer followeeId : followees) {
                if (null == userTweets.get(followeeId) || userTweets.get(followeeId).size() == 0) {
                    continue;
                }
                ques.add(userTweets.get(followeeId));
            }
        }

        ArrayDeque myQue = userTweets.get(userId);
        if (null != myQue && myQue.size() > 0) {
            ques.add(myQue);
        }

        if (ques.size() == 0) {
            return new ArrayList<>(0);
        }

        ArrayDeque[] copy = new ArrayDeque[ques.size()];
        int i = 0;
        for (ArrayDeque que : ques) {
            ArrayDeque<int[]> q = new ArrayDeque<>(que);
            copy[i++] = q;
        }

        // top10的news
        //System.out.println("userId="+userId+" top10news=");
        List<Integer> newFeeds = new ArrayList<>(10);
        for (int j = 0; j < 10; j++) {
            int max = getMax(copy);
            if (max == -1) {
                break;
            }
            newFeeds.add(max);
            //System.out.print(max+" ");
        }
        //System.out.println();
        return newFeeds;
    }

    int getMax(ArrayDeque[] copy) {
        int max = -1;
        int maxIdx = -1;
        for (int i = 0; i < copy.length; i++) {
            ArrayDeque<int[]> que = copy[i];
            if (que.isEmpty()) {
                continue;
            }
            if (max < que.peek()[1]) {
                max = que.peek()[1];
                maxIdx = i;
            }
        }
        if (maxIdx > -1) {
            ArrayDeque<int[]> q = copy[maxIdx];
            return q.removeFirst()[0];
        }
        return max;
    }

    public void follow(int followerId, int followeeId) {
        HashSet<Integer> followees = userFollowees.getOrDefault(followerId, new HashSet<>());
        followees.add(followeeId);
        userFollowees.put(followerId, followees);
    }

    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> followees = userFollowees.getOrDefault(followerId, new HashSet<>());
        followees.remove(followeeId);
    }
}
