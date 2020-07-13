package com.dd.ddfgm.controller;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.ActivityInfo;
import com.dd.ddfgm.entity.ActivityLog;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.enums.operatStatus;
import com.dd.ddfgm.mapper.ActivityMapper;
import com.dd.ddfgm.service.AccountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityMapper activityMapper;

    @RequestMapping("/all")
    public ModelAndView getActivity() {
        ModelAndView modelAndView = new ModelAndView("activity");
        List<ActivityLog> allActivityLog = activityMapper.getAllActivityLog();
        //可添加的活动
        List<ActivityInfo> allActivityInfo = activityMapper.getAllActivityInfo();
        modelAndView.addObject("allActivityLog", allActivityLog);
        modelAndView.addObject("allActivityInfo", allActivityInfo);
        return modelAndView;
    }

    @RequestMapping(value = "/delete")
    public String deleteActivity(@RequestParam(value = "log_id") Integer log_id) {
        if (null != log_id) {
            Integer result = activityMapper.deleteActivityLog(log_id);
        }
        return "redirect:/activity/all";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Map<String, Integer> addActivity(@RequestParam(value = "event_type") Integer event_type,
                              @RequestParam(value = "parameter1") Integer parameter1,
                              HttpServletResponse httpServletResponse) throws IOException {
        Integer result = -1;
        if (parameter1 != 1 && parameter1 % 100 != 0) {
            result = -2;
        }
        else if (null != event_type && parameter1 != null) {
            result = activityMapper.addActivityLog(event_type, parameter1);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

}
