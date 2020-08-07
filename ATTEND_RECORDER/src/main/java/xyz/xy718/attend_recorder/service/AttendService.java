package xyz.xy718.attend_recorder.service;

import org.springframework.stereotype.Service;
import xyz.xy718.attend_recorder.dao.repository.AttendRecordRepository;
import xyz.xy718.attend_recorder.model.AttendRecord;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author: Xy718
 * @date: 2020/8/7 14:55
 * @description:
 */
@Service
public class AttendService {
    @Resource
    AttendRecordRepository arRepo;

    public Optional<Boolean> goAttend(String code,String timeStamp){
        int coded;
        long timeStamped;
        try {
            coded=Integer.parseInt(code);
            timeStamped=Long.parseLong(timeStamp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Optional.ofNullable(false);
        }
        Optional<AttendRecord> isAlready=arRepo.findByTargetIdAndRecordTime(coded,timeStamped);
        if(isAlready.isPresent()){
            return Optional.ofNullable(true);
        }
        AttendRecord record=arRepo.save(new AttendRecord(coded,0,timeStamped));
        if(record!=null){
            return Optional.ofNullable(true);
        }
        return Optional.ofNullable(false);
    }
}
