package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.query.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author caitao
 * @since 2020-02-18
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageConditionList(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher);
}
