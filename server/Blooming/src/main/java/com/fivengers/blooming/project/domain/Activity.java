package com.fivengers.blooming.project.domain;

import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Activity extends Project {

    private String albumImgUrl;
    private String tracklistImgUrl;
    private String compositionImgUrl;

    @Builder
    public Activity(Long id,
            String name,
            Long fundingAmount,
            Long targetAmount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String introduction,
            String description,
            String teaserVideoUrl,
            Integer revenuePercent,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Artist artist,
            String albumImgUrl,
            String tracklistImgUrl,
            String compositionImgUrl) {
        super(id, name, fundingAmount, targetAmount, startedAt, endedAt,
                introduction, description, teaserVideoUrl, revenuePercent,
                createdAt, modifiedAt, artist);
        this.albumImgUrl = albumImgUrl;
        this.tracklistImgUrl = tracklistImgUrl;
        this.compositionImgUrl = compositionImgUrl;
    }
}
