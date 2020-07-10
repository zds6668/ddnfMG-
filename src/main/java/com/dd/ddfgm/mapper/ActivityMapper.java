package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper {
    // 正在进行的活动
    List<ActivityLog> getAllActivityLog();

    // 可以添加的活动
    List<ActivityInfo> getAllActivityInfo();

    Integer deleteActivityLog(Integer log_id);
    Integer addActivityLog(Integer event_type, Integer parameter1);
}
