package guru.nickthompson.redditapi;

import java.util.Date;

/**
 * Created by nick on 9/29/17.
 */

public class Comment {
    private final Post post;
    private final String username;
    private final Date timestamp;
    private final String body;


    public Comment(Post post, String username, Date timestamp, String body) {
        this.post = post;
        this.username = username;
        this.timestamp = timestamp;
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public Date getTimeStamp() {
        return this.timestamp;
    }

    public String getBody() {
        return this.body;
    }
}
