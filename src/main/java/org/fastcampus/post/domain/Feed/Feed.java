package org.fastcampus.post.domain.Feed;

import java.time.LocalDateTime;

public class Feed {
    private String subject;
    private String authorName;
    private String authorProfileImageUrl;
    private Integer likeCount;
    private Integer commentCount;
    private Boolean edited;
    private LocalDateTime regDtm;
    private Boolean liked;
}
