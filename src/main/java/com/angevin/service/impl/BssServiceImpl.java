package com.angevin.service.impl;

import com.angevin.domain.ExcelReconciliaListVo;
import com.angevin.domain.ReconciliaVO;
import com.angevin.domain.Result;
import com.angevin.entity.PageBean;
import com.angevin.entity.Reconcilia;
import com.angevin.mapper.ReconciliaMapper;
import com.angevin.service.BssService;
import com.angevin.utils.ExportExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created  by  Angevin.
 * Date: 2019-10-30 18:40
 * description:
 *
 * @author Angevin
 */
@Service
public class BssServiceImpl implements BssService {

    @Autowired
    private ReconciliaMapper reconciliaMapper;

    @Override
    public List<Reconcilia> findAll() {
        return null;
    }

    @Override
    public List<Reconcilia> findById(Long id) {
        return reconciliaMapper.findById(id);
    }

    @Override
    public void create(Reconcilia reconcilia) {
        reconcilia.setCreateTime(new Timestamp(System.currentTimeMillis()));
        reconciliaMapper.create(reconcilia);
    }

    @Override
    public void update(Reconcilia reconcilia) {
        reconciliaMapper.update(reconcilia);
    }

    @Override
    public void delete(Long... ids) {
        for (Long id : ids) {
            reconciliaMapper.delete(id);
        }
    }

    /**
     * 分页查询-条件查询方法
     *
     * @param reconcilia    查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */

    @Override
    public PageBean findByPage(ReconciliaVO reconcilia, int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<Reconcilia> page = reconciliaMapper.findByPage(reconcilia);

        return new PageBean(page.getTotal(), page.getResult());
    }


    @Override
    public List<Reconcilia> findByValueBean(ReconciliaVO reconcilia) {
        return reconciliaMapper.findByValueBean(reconcilia);
    }

    @Override
    public Result exportBssDataList(OutputStream out, ReconciliaVO vo) {
        Result result = new Result (false,"创建报表失败");
        List<String> fieldNameList = new ArrayList<String>();
        // 要显示的字段对应的表头显示list
        List<String> headerList = new ArrayList<String> ();

        Field[] fields = ExcelReconciliaListVo.class.getDeclaredFields();
        for (Field field : fields) {
            fieldNameList.add(field.getName());
            headerList.add(getDisplayFieldName(field.getName(), null));
        }

        List<Reconcilia> list = this.findByValueBean(vo);

        List<ExcelReconciliaListVo> resultList = new ArrayList<ExcelReconciliaListVo> ();
        for (Reconcilia reconcilia: list) {

            reconcilia.setActType(formatterActType(reconcilia.getActType()));
            reconcilia.setInstitutionCode(formatterInstitutionCode(reconcilia.getInstitutionCode()));

            resultList.add(new ExcelReconciliaListVo(reconcilia));
        }

        ExportExcel<ExcelReconciliaListVo> export = new ExportExcel<ExcelReconciliaListVo> ();
        try {
            export.exportExcept("BSS对账列表", resultList, headerList, fieldNameList, out);
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        result = new Result (true,"创建报表成功");
        return result;
    }
    private static   String formatterActType(String val) {

        if (val.equals("A")) {
            return "开通";
        } else if (val.equals("R")){
            return "退订";
        } else if (val.equals("X")) {
            return "不变";
        }else{
            return "--";
        }
    }
    private  static String formatterInstitutionCode(String val) {
        if (val.equals("0000") ){
            return "外勤管家";
        } else if (val.equals("0001")) {
            return "初定外平台";
        } else if (val.equals("0002")) {
            return "音乐公司";
        }else if (val.equals("0003") ){
            return "产创云（COP平台）";
        }else if (val.equals("0004") ){
            return "MDM平台";
        }else{
            return "--";
        }
    }

    /**
     * 根据fieldName获取表头显示信息
     *
     * @param fieldName
     * @param language
     * @return
     */
    private String getDisplayFieldName(String fieldName, String language) {
        String displayName = "";
        if ("id".equals(fieldName)) {
            displayName = "序号";
        }
        if ("serialNumber".equals(fieldName)) {
            displayName = "业务号码";
        }
        if ("custId".equals(fieldName)) {
            displayName = "客户标识";
        }
        if ("custName".equals(fieldName)) {
            displayName = "客户名称";
        }
        if ("localNetId".equals(fieldName)) {
            displayName = "归属地";
        }
        if ("acceptDate".equals(fieldName)) {
            displayName = "预约受理时间";
        }
        if ("prodId".equals(fieldName)) {
            displayName = "产品编码";
        }
        if ("pricePlanId".equals(fieldName)) {
            displayName = "资费编码";
        }
        if ("actType".equals(fieldName)) {
            displayName = "业务操作";
        }
        if ("effDate".equals(fieldName)) {
            displayName = "生效时间";
        }
        if ("expDate".equals(fieldName)) {
            displayName = "失效时间";
        }
        if ("institutionCode".equals(fieldName)) {
            displayName = "接收机构";
        }
        if ("type".equals(fieldName)) {
            displayName = "";
        }
        if ("type".equals(fieldName)) {
            displayName = "";
        }
        return displayName;
    }
}
