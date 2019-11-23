package com.angevin.service;

import com.angevin.domain.ReconciliaVO;
import com.angevin.domain.Result;
import com.angevin.entity.PageBean;
import com.angevin.entity.Reconcilia;
import java.io.OutputStream;
import java.util.List;

/**
 * Created  by  Angevin.
 * Date: 2019-10-30 18:37
 * description:
 *
 * @author Angevin
 */
public interface BssService extends BaseService<Reconcilia> {

    /**
     * 分页查询
     * @param reconcilia 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(ReconciliaVO reconcilia, int pageCode, int pageSize);

    // 导出资产列表
    Result exportBssDataList(OutputStream out, ReconciliaVO vo);


    List<Reconcilia> findByValueBean(ReconciliaVO reconcilia);

}
