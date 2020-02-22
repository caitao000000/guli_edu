package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author caitao
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    // 1 . 查询所有讲师
    @GetMapping
    public R getAllTeacherList(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    // 2.逻辑删除讲师
    @DeleteMapping("{id}")
    public R deleteTeacher(@PathVariable String id){
        eduTeacherService.removeById(id);
        return R.ok();
    }

    //3.分页查询讲师列表
    @GetMapping("/pagelist/{page}/{limit}")
    public R getPageListTeacher(@PathVariable("page") Integer page,
                                @PathVariable("limit") Integer limit){
        // 创建page对象，传递两个参数
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        // 调用分页方法查询
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return  R.ok().data("total",total).data("items",records);

    }

    //  4.多条件查询+分页
    @PostMapping("/moreCondition/{page}/{limit}")
    public R getMoreConditonPage(@PathVariable("page") Integer page,
                                 @PathVariable("limit") Integer limit,
                                 @RequestBody(required = false) QueryTeacher queryTeacher){
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        eduTeacherService.pageConditionList(pageTeacher,queryTeacher);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return  R.ok().data("total",total).data("items",records);
    }

    //  5.添加讲师
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save ){
            return R.ok();
        }else {
            return R.error();
        }
    }

    // 6.根据id查询讲师
    @GetMapping("/getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher",eduTeacher);
    }

    // 7.根据id 修改讲师
    @PutMapping("updateTeacher/{id}")
    public R updateTeacher(@PathVariable String id,@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

