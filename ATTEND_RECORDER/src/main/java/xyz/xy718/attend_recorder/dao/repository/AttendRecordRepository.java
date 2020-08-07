package xyz.xy718.attend_recorder.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xy718.attend_recorder.model.AttendRecord;

import java.util.Optional;

/**
 * @author: Xy718
 * @date: 2020/8/7 14:57
 * @description:
 */
public interface AttendRecordRepository extends JpaRepository<AttendRecord,Long> {

    Optional<AttendRecord> findByTargetIdAndRecordTime(int targetId,long recordTime);
}
