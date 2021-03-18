package com.yunseong.notification.domain;

import com.yunseong.notification.controller.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select new com.yunseong.notification.controller.NotificationResponse(n.id, n.title, n.sender, n.receiver) from Notification n where n.sender = :sender")
    Page<NotificationResponse> findAllBySender(String sender);

    @Query("select new com.yunseong.notification.controller.NotificationResponse(n.id, n.title, n.sender, n.receiver) from Notification n where n.receiver = :receiver")
    Page<NotificationResponse> findAllByReceiver(String receiver);
}
