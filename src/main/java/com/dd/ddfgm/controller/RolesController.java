package com.dd.ddfgm.controller;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.OnlineAccount;
import com.dd.ddfgm.entity.RankDTO;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.enums.operatStatus;
import com.dd.ddfgm.mapper.RoleMapper;
import com.dd.ddfgm.miaoshaproject.controller.viewobject.ItemVO;
import com.dd.ddfgm.miaoshaproject.controller.viewobject.RolePkVO;
import com.dd.ddfgm.miaoshaproject.dao.cdkeyDOMapper;
import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyVO;
import com.dd.ddfgm.miaoshaproject.error.BusinessException;
import com.dd.ddfgm.miaoshaproject.error.EmBusinessError;
import com.dd.ddfgm.miaoshaproject.response.CommonReturnType;
import com.dd.ddfgm.miaoshaproject.service.model.ItemModel;
import com.dd.ddfgm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleMapper roleMapper;

    @RequestMapping("/all")
    @ResponseBody
    public List<OnlineAccount> getAllRoles(HttpServletRequest request) {
        List<OnlineAccount> allAccounts = accountService.getAllAccounts();
        if (request.getUserPrincipal().getName().equalsIgnoreCase("111111"))
            return accountService.getAllAccountsNoHidden();
        return allAccounts;
    }

    @RequestMapping("/online")
    @ResponseBody
    public List<OnlineAccount> getOnlineRoles(HttpServletRequest request) {
        List<OnlineAccount> onlineAccounts = accountService.getOnlineAccounts();
        if (request.getUserPrincipal().getName().equalsIgnoreCase("111111"))
            return accountService.getOnlineAccountsNoHidden();
        return onlineAccounts;
    }

    @RequestMapping("/info")
    public ModelAndView getRolesInfo(Map<String, Object> map) {
        Integer accountNum = accountService.getAccountNum();
        Integer loginAccountNum = accountService.getLoginAccounts();
        map.put("accountNum", accountNum);
        map.put("loginAccountNum", loginAccountNum);
        map.put("status", operatStatus.FAILED.getStatus());

        return new ModelAndView("roles", map);
    }

    @RequestMapping("/rank")
    public ModelAndView getRank(Map<String, Object> map) {
        List<RankDTO> rankList = accountService.getRankList();
        map.put("rankList", rankList);
        return new ModelAndView("ranklist", map);
    }

    @RequestMapping("/pk")
    public ModelAndView getPkReword(Map<String, Object> map,
                                    HttpServletRequest httpServletRequest) {
        //获取用户的登陆信息
        Account account = accountService.getAccountInfo(httpServletRequest.getUserPrincipal().getName());
        List<RolePkVO> rolesPk = roleMapper.getRolesPk(account.getUID(), null);
        map.put("status", operatStatus.FAILED.getStatus());
        map.put("rolesPk", rolesPk);
        return new ModelAndView("miaosha/mypk", map);
    }

    //兑换pk胜场
    @RequestMapping(value = "/getPk", method = {RequestMethod.POST})
    @ResponseBody
    @Transactional
    public CommonReturnType getPk(@RequestParam(name = "characno") Integer characno,
                                  HttpServletRequest httpServletRequest) {

        //获取用户的登陆信息
        Account account = accountService.getAccountInfo(httpServletRequest.getUserPrincipal().getName());
        List<RolePkVO> rolesPk = roleMapper.getRolesPk(account.getUID(), characno);
        if (rolesPk == null || rolesPk.size() == 0)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"角色信息有误");
        Integer winNum = rolesPk.get(0).getWin();
        if (winNum <= 0)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"胜场不够你换锤子");
        for (RolePkVO rolePkVO : rolesPk) {
            int result = roleMapper.setPkWin(characno, 0, rolePkVO.getPvp_count() - winNum, rolePkVO.getPlay_count() - winNum);
            if (result <= 0)
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"胜场兑换出错");
        }
        // 一个胜场换1000点卷
        int result = accountService.rechargeDD(account.getAccountname(), 1000*winNum);
        return CommonReturnType.create(rolesPk.get(0));
    }

}
