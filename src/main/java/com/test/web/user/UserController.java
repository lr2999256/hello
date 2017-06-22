package com.test.web.user;

import com.test.persistence.beans.TTestUser;
import com.test.persistence.mappers.TTestUserMapper;
import com.test.vo.UserVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Rui on 2017/5/27.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TTestUserMapper tTestUserMapper;

    @RequestMapping("list")
    public String list(UserVo vo,Model model) {
        RowBounds rb = new RowBounds(vo.getStartIndex(), vo.getPageSize());
        List<TTestUser> users =  tTestUserMapper.queryByCondition(vo,rb);
        int totalCount = tTestUserMapper.queryInfoCount(vo);
        vo.setTotalCount(totalCount);
        int pageCount;
        if(totalCount%vo.getPageSize()>0){
            pageCount = totalCount/vo.getPageSize()+1;
        }else {
            pageCount = totalCount/vo.getPageSize();
        }
        if(pageCount == 0){
            pageCount =1;
        }
        vo.setPageCount(pageCount);
        model.addAttribute("users",users);
        model.addAttribute("vo",vo);
        return "user/list";
    }

    @RequestMapping("add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("addres")
    public String addres(UserVo vo,Model model) {
        tTestUserMapper.insertSelective(vo.gettTestUser());

        UserVo newVo = new UserVo();
        RowBounds rb = new RowBounds(newVo.getStartIndex(),vo.getPageSize());
        List<TTestUser> users =  tTestUserMapper.queryByCondition(newVo,rb);
        int totalCount = tTestUserMapper.queryInfoCount(newVo);
        vo.setTotalCount(totalCount);
        int pageCount;
        if(totalCount%vo.getPageSize()>0){
            pageCount = totalCount/vo.getPageSize()+1;
        }else {
            pageCount = totalCount/vo.getPageSize();
        }
        if(pageCount == 0){
            pageCount =1;
        }
        vo.setPageCount(pageCount);
        model.addAttribute("users",users);
        model.addAttribute("vo",vo);
        return "user/list";
    }

    @RequestMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        TTestUser user =  tTestUserMapper.selectByPrimaryKey(id);
        model.addAttribute("user",user);
        return "user/upd";
    }

    @RequestMapping("updateres")
    public String updateres(UserVo vo,Model model) {
        tTestUserMapper.updateByPrimaryKey(vo.gettTestUser());

        UserVo newVo = new UserVo();
        RowBounds rb = new RowBounds(newVo.getStartIndex(),vo.getPageSize());
        List<TTestUser> users =  tTestUserMapper.queryByCondition(newVo,rb);
        int totalCount = tTestUserMapper.queryInfoCount(newVo);
        int pageCount;
        if(totalCount%vo.getPageSize()>0){
            pageCount = totalCount/vo.getPageSize()+1;
        }else {
            pageCount = totalCount/vo.getPageSize();
        }
        if(pageCount == 0){
            pageCount =1;
        }
        vo.setPageCount(pageCount);
        vo.setTotalCount(totalCount);
        model.addAttribute("users",users);
        model.addAttribute("vo",vo);
        return "user/list";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id,Model model) {
        TTestUser user =  tTestUserMapper.selectByPrimaryKey(id);
        model.addAttribute("user",user);
        return "user/del";
    }

    @RequestMapping("deleteres")
    public String deleteres(UserVo vo,Model model) {
        tTestUserMapper.deleteByPrimaryKey(vo.getId());

        UserVo newVo = new UserVo();
        RowBounds rb = new RowBounds(newVo.getStartIndex(),vo.getPageSize());
        List<TTestUser> users =  tTestUserMapper.queryByCondition(newVo,rb);
        int totalCount = tTestUserMapper.queryInfoCount(newVo);
        int pageCount;
        if(totalCount%vo.getPageSize()>0){
            pageCount = totalCount/vo.getPageSize()+1;
        }else {
            pageCount = totalCount/vo.getPageSize();
        }
        if(pageCount == 0){
            pageCount =1;
        }
        vo.setPageCount(pageCount);
        vo.setTotalCount(totalCount);
        model.addAttribute("users",users);
        model.addAttribute("vo",vo);
        return "user/list";
    }
}
