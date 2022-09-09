package com.wwj.common.module.test.controller;

import cn.craccd.mongoHelper.bean.Page;
import cn.craccd.mongoHelper.bean.SortBuilder;
import cn.craccd.mongoHelper.utils.CriteriaAndWrapper;
import cn.craccd.mongoHelper.utils.CriteriaOrWrapper;
import cn.craccd.mongoHelper.utils.MongoHelper;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.UserInfo;
import com.mongodb.client.result.UpdateResult;
import com.wwj.common.module.question.entity.TestInfo;
import com.wwj.common.module.question.entity.UserTest;
import com.wwj.core.api.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @program: frame
 * @className: TestInfoController
 * @description: 试题基本信息
 * @author: 122439195@qq.com
 * @date: 2021-09-23 10:20
 **/
@RestController
@RequestMapping("/test")
@Api(value = "试题API", tags = {"试题管理"})
public class TestInfoController {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private MongoHelper mongoHelper;

    @PostMapping("/add")
    public void add() {
        UserTest userTest = new UserTest();
        userTest.setUuid(IdUtil.simpleUUID());
        userTest.setUserName("小明");
        userTest.setPassword("123456");
        userTest.setAge(11);
        UserTest save = mongoTemplate.save(userTest);
        System.out.println(save);
    }

    @PostMapping("/find")
    public ApiResult<TestInfo> find() {
        Query query = new Query(Criteria.where("uuid").is("f125eaba407645589ee89b74908387c6"));
        Update update = new Update().set("questionName", "这是一道题")
                .set("answer","这是答案")
                .set("answerAbs", "这是解析");
        mongoTemplate.updateFirst(query, update, TestInfo.class);
        // 查询一条满足条件的数据
        TestInfo result = mongoTemplate.findOne(query, TestInfo.class);
        return ApiResult.ok(result);
    }

    @PostMapping("/list")
    public ApiResult<List<TestInfo>> list() {
        int offset=0;
        int limit=20;
        Pattern pattern = Pattern.compile("^.*你猜.*$", Pattern.CASE_INSENSITIVE);
        Criteria criteria= Criteria.where("answer").regex(pattern);
        Query query = new Query(criteria);
        query.skip(offset).limit(limit);
        List<TestInfo> ret= mongoTemplate.find(query,TestInfo.class);
        return ApiResult.ok(ret);
    }

    @PostMapping("/find/list")
    public ApiResult<?> findList(){
        Page<TestInfo> page = new Page<>();
        page.setCurr(1);
        page.setLimit(10);
        CriteriaAndWrapper criteriaAndWrapper = new CriteriaAndWrapper();
        String word = "这";
        if (StrUtil.isNotEmpty(word)) {
            criteriaAndWrapper.and(new CriteriaOrWrapper().like(TestInfo::getAnswer, word));
        }
        List<TestInfo> list = mongoHelper.findListByQuery(criteriaAndWrapper, TestInfo.class);
        page = mongoHelper.findPage(criteriaAndWrapper, new SortBuilder(), page, TestInfo.class);
        return ApiResult.ok(page);
    }
}
