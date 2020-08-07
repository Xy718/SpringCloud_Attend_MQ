package xyz.xy718.attend_recorder.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 签到记录
 * @author: Xy718
 * @date: 2020/8/7 14:58
 * @description:
 */
@Data
@Entity
@Table(name = "attend_record")
public class AttendRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private long recordId;
    @Column(name = "target_id")
    private int targetId;
    @Column(name = "target_type")
    private int targetType;
    @Column(name = "record_time")
    private long recordTime;

    public AttendRecord(int target_id,int target_type,long record_time){
        this.targetId=target_id;
        this.targetType=target_type;
        this.recordTime=record_time;
    }
}
