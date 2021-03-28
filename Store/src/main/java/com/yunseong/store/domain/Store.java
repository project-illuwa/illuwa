package com.yunseong.store.domain;

import com.yunseong.store.controller.StoreUpdateRequest;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String notice;

    @NonNull
    @DateTimeFormat(pattern = "H:mm")
    private LocalDateTime minTime;

    @NonNull
    @DateTimeFormat(pattern = "H:mm")
    private LocalDateTime maxTime;

    @Setter
    @Embedded
    private Location location;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime updatedTime;

    public void update(StoreUpdateRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.notice = request.getNotice();
        this.minTime = request.getMinTime();
        this.maxTime = request.getMaxTime();
    }
}
