package com.ormi.cookudasse.notice.infrastructure;

import com.ormi.cookudasse.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n FROM Notice n ORDER BY n.createdAt DESC LIMIT :limit")
    List<Notice> findLatestNotices(@Param("limit") int limit);
}
