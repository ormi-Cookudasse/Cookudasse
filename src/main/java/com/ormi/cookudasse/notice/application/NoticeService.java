package com.ormi.cookudasse.notice.application;

import com.ormi.cookudasse.notice.domain.Notice;
import com.ormi.cookudasse.notice.dto.request.NoticeRequest;
import com.ormi.cookudasse.notice.infrastructure.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public void createNotice(NoticeRequest noticeRequest) {
        Notice notice = Notice.builder()
                .title(noticeRequest.getTitle())
                .content(noticeRequest.getContent())
                .build();
        noticeRepository.save(notice);
    }

    @Transactional(readOnly = true)
    public List<Notice> getNNotices(int n) {
        return noticeRepository.findLatestNotices(n);
    }

    @Transactional(readOnly = true)
    public Notice getNoticeById(Long noticeId) {
    return noticeRepository.findById(noticeId).orElseThrow(() -> new RuntimeException("Notice not found"));
    }

    @Transactional
    public void updateNotice(Long noticeId, NoticeRequest noticeRequest) {
        Notice findNotice = noticeRepository.findById(noticeId).orElseThrow(() -> new RuntimeException("Notice not found"));
        findNotice.updateNotice(noticeRequest.getTitle(), noticeRequest.getContent());
    }

    @Transactional
    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }
}
