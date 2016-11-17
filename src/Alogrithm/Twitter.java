package Alogrithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Twitter {

	private static class Tweet {
	    int timestamp;
	    int tweetId;

	    public Tweet(int tweetId, int timestamp) {
	        this.tweetId = tweetId;
	        this.timestamp = timestamp;
	    }
	}
	
	private Map<Integer, Set<Integer>> follow = new HashMap<>(); // ÿһ���û������ķ�˿
	private Map<Integer, Deque<Tweet>> tweets = new HashMap<>(); // ÿһ���û�ά��һ�������б�
	private AtomicInteger timestamp;

	/** Initialize your data structure here. */
	public Twitter() {
	    timestamp = new AtomicInteger(0);
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
	    Tweet tweet = new Tweet(tweetId, timestamp.getAndIncrement()); // �½�һƪ����
	    tweets.putIfAbsent(userId, new ArrayDeque<Tweet>(11)); 
	    Deque queue = tweets.get(userId); //ȡ���û���Ӧ�������б�
	    queue.offer(tweet);
	    if (queue.size() > 10)
	        queue.poll();
	}

	/** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
	public List<Integer> getNewsFeed(int userId) {
	    List<Integer> newsFeed = new ArrayList<> (10);
	    Set<Integer> followers = follow.getOrDefault(userId, new HashSet<> ()); //��ǰ�û��ķ�˿
	    
	    Comparator<Tweet> comp = new Comparator<Tweet>(){
			@Override
			public int compare(Tweet o1, Tweet o2) {
				return o1.timestamp - o2.timestamp;
			}
	    };
	    PriorityQueue<Tweet> pq = new PriorityQueue<>(20, comp);
	    
	    pq.addAll(tweets.getOrDefault(userId, new ArrayDeque<Tweet> ())); //pq�м����˵�ǰuser������
	    for (int follower : followers) { //pq�м����˵�ǰuser��˿������
	        pq.addAll(tweets.getOrDefault(follower, new ArrayDeque<Tweet>())); 
	        while (pq.size() > 10)
	            pq.poll(); // ��С��ֻ����ǰʮ��
	    }
	    while (!pq.isEmpty())
	        newsFeed.add(0, pq.poll().tweetId);
	    return newsFeed;
	}

	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int followerId, int followeeId) {
	    if (followerId == followeeId) // �Լ����ܹ�ע�Լ�
	        return;
	    follow.putIfAbsent(followerId, new HashSet<>());
	    follow.get(followerId).add(followeeId); // ��ӵ���Ӧ�б�
	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
	    if (follow.containsKey(followerId))
	        follow.get(followerId).remove(followeeId); // �ӹ�ע�б����Ƴ�
	}
    
    
    public static void main(String[] args) {
    	Twitter twitter = new Twitter();

    	// User 1 posts a new tweet (id = 5).
    	twitter.postTweet(1, 5);

    	// User 1's news feed should return a list with 1 tweet id -> [5].
    	System.out.println(twitter.getNewsFeed(1));

    	// User 1 follows user 2.
    	twitter.follow(1, 2);

    	// User 2 posts a new tweet (id = 6).
    	twitter.postTweet(2, 6);

    	// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
    	// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
    	System.out.println(twitter.getNewsFeed(1));

    	// User 1 unfollows user 2.
    	twitter.unfollow(1, 2);

    	// User 1's news feed should return a list with 1 tweet id -> [5],
    	// since user 1 is no longer following user 2.
    	System.out.println(twitter.getNewsFeed(1));
	}
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
