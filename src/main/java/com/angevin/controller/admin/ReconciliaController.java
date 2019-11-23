package com.angevin.controller.admin;

import com.angevin.domain.BssDataInfo;
import com.angevin.domain.BssResult;
import com.angevin.domain.ProOrderReq;
import com.angevin.domain.ProOrderResp;
import com.angevin.domain.ReconciliaVO;
import com.angevin.domain.Result;
import com.angevin.domain.UniBssBody;
import com.angevin.domain.UniBssHead;
import com.angevin.entity.PageBean;
import com.angevin.entity.Reconcilia;
import com.angevin.service.BssService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @auther Angevin
 * @date 2019年11月1日 16:41:43
 */
@RestController
@RequestMapping("/reconcilia")
public class ReconciliaController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(ReconciliaController.class);

    @Autowired
    private BssService bssService;

    /**
     * 分页查询
     *
     * @param reconcilia    查询条件
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    @RequestMapping("/findByConPage")
    public PageBean findByConPage(ReconciliaVO reconcilia,
                                  @RequestParam(value = "pageCode", required = false) int pageCode,
                                  @RequestParam(value = "pageSize", required = false) int pageSize) {
        return bssService.findByPage(reconcilia, pageCode, pageSize);
    }

    /**
     * 新增
     *
     * @param reconcilia
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result create(@RequestBody Reconcilia reconcilia) {
        try {
            bssService.create(reconcilia);
            return new Result(true, "创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
    /**
     * 新增
     *
     * @param bssdata
     * @return
     */
    @PostMapping(value="/syncData",produces = "application/json;charset=UTF-8")
    public BssResult syncData(@RequestBody BssDataInfo bssdata) {
        Gson gs = new Gson();
        BssResult result = new BssResult();
        ProOrderResp pro = new  ProOrderResp();
        UniBssBody body = new UniBssBody();
        UniBssHead head = new UniBssHead();
        body.setProOrderResp(pro);
        result.setUniBssBody(body);
        result.setUniBssHead(head);
        logger.info("接收的数据："+gs.toJson(bssdata));

        try {
            if (null == bssdata){
                logger.info("调用syncData接口的数据为空值！");
                head.setRespCode("0000");
                head.setRespDesc("调用数据为空");
                pro.setResultCode("0000");
                pro.setResultMsg("调用数据为空");
                return result;
            }
            //0004表示mdm
            if (bssdata.getUniBssBody().getProOrderReq().getInstitutionCode().equals("0004")){
                ProOrderReq pr = bssdata.getUniBssBody().getProOrderReq();
                Reconcilia reconcilia = new Reconcilia();
                reconcilia.setSerialNumber(pr.getSerialNumber());
                reconcilia.setCustId(pr.getCustId());
                reconcilia.setCustName(pr.getCustName());
                reconcilia.setLocalNetId(pr.getLocalNetId());
                reconcilia.setAcceptDate(pr.getAcceptDate());
                //和“能开”确认每次只会传一个套餐信息。
                reconcilia.setProdId(pr.getPricePlanInfos().get(0).getProdId());
                reconcilia.setPricePlanId(pr.getPricePlanInfos().get(0).getPricePlanId());
                reconcilia.setActType(pr.getActType());
                reconcilia.setEffDate(pr.getEffDate());
                reconcilia.setExpDate(pr.getExp_date());
                reconcilia.setInstitutionCode(pr.getInstitutionCode());
                bssService.create(reconcilia);
            }else {
                logger.info("忽略的数据："+gs.toJson(bssdata));
            }
        } catch (Exception e) {
            e.printStackTrace();
            head.setRespCode("9001");
            head.setRespDesc("服务器内部错误");
            pro.setResultCode("9001");
            pro.setResultMsg("服务器内部错误");
            return result;
        }
        head.setRespCode("0000");
        head.setRespDesc("调用数据成功");
        pro.setResultCode("0000");
        pro.setResultMsg("调用数据成功");
        return result;
    }

    /**
     * 更新数据成功
     *
     * @param reconcilia
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Reconcilia reconcilia) {
        try {
            bssService.update(reconcilia);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long... ids) {
        try {
            bssService.delete(ids);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public List<Reconcilia> findById(@RequestParam(value = "id", required = false) Long id) {
        return bssService.findById(id);
    }

    /**
     * 导出
     * @param vo
     * @return
     */
    @RequestMapping(value = "/exportDailyReport", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity exportDevice(ReconciliaVO vo, HttpServletResponse response) throws Exception {

        Gson gs = new Gson();
        logger.info("vo:"+gs.toJson(vo));
        String ctxPath = getRequest().getSession().getServletContext().getRealPath("/");

        String exportPath = "doc/bssData/";
        String fileName = Math.random() + ".xls";
        String realName = "bssData.xls";
        String filePathName = ctxPath + exportPath + fileName;
        logger.info("vo:"+filePathName);

        File checkPath = new File(ctxPath + exportPath);
        if (!checkPath.exists()) {
            checkPath.mkdirs();
        }
        File checkFile = null;
        // 生成excel的路径
        FileOutputStream out = new FileOutputStream(filePathName);
        BufferedInputStream fis = null;
        BufferedOutputStream fos = null;
        try {
            Result result = bssService.exportBssDataList(out, vo);

            if (result.isSuccess()) {
                checkFile = new File(filePathName);
                Long fileLength = checkFile.length();

                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(realName.getBytes("utf-8"), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileLength));
                fis = new BufferedInputStream(new FileInputStream(filePathName));
                fos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = fis.read(buff, 0, buff.length))) {
                    fos.write(buff, 0, bytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (checkFile != null && checkFile.exists()) {
                checkFile.delete();// 清除文件
            }
        }
        return null;
    }
}
