
package com.joymain.jecs.pm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.pm.dao.JpmConfigDetailedDao;
import com.joymain.jecs.pm.dao.JpmConfigSpecDetailedDao;
import com.joymain.jecs.pm.dao.JpmMemberConfigDao;
import com.joymain.jecs.pm.dao.JpmProductWineTemplateDao;
import com.joymain.jecs.pm.model.JpmConfigDetailed;
import com.joymain.jecs.pm.model.JpmConfigSpecDetailed;
import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.model.JpmProductWineTemplate;
import com.joymain.jecs.pm.service.JpmConfigDetailedManager;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.dao.JpoMemberOrderListDao;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmConfigDetailedManagerImpl extends BaseManager implements JpmConfigDetailedManager {
    private JpmConfigDetailedDao jpmConfigDetailedDao;
    
    JpmConfigSpecDetailedDao jpmConfigSpecDetailedDao;
    
    JpmMemberConfigDao jpmMemberConfigDao;
    
    JpoMemberOrderListDao jpoMemberOrderListDao;
    
    JpmProductWineTemplateDao jpmProductWineTemplateDao;
    
    JpoMemberOrderDao jpoMemberOrderDao;

    /** 保存全新订单 */
    private static final String SAVE_CONFIG = "0";
    
    /** 保存订单下新规格 */
    private static final String SAVE_SPEC_CONFIG = "1";
    
    /** 保存规格下详细信息 */
    private static final String SAVE_DETAILED = "2";
    
    public void setJpmConfigSpecDetailedDao(JpmConfigSpecDetailedDao jpmConfigSpecDetailedDao)
    {
        this.jpmConfigSpecDetailedDao = jpmConfigSpecDetailedDao;
    }

    public void setJpmMemberConfigDao(JpmMemberConfigDao jpmMemberConfigDao)
    {
        this.jpmMemberConfigDao = jpmMemberConfigDao;
    }

    public void setJpoMemberOrderListDao(JpoMemberOrderListDao jpoMemberOrderListDao)
    {
        this.jpoMemberOrderListDao = jpoMemberOrderListDao;
    }

    public void setJpmProductWineTemplateDao(JpmProductWineTemplateDao jpmProductWineTemplateDao)
    {
        this.jpmProductWineTemplateDao = jpmProductWineTemplateDao;
    }

    public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao)
    {
        this.jpoMemberOrderDao = jpoMemberOrderDao;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmConfigDetailedDao(JpmConfigDetailedDao dao) {
        this.jpmConfigDetailedDao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmConfigDetailedManager#getJpmConfigDetaileds(com.joymain.jecs.pm.model.JpmConfigDetailed)
     */
    public List getJpmConfigDetaileds(final JpmConfigDetailed jpmConfigDetailed) {
        return jpmConfigDetailedDao.getJpmConfigDetaileds(jpmConfigDetailed);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmConfigDetailedManager#getJpmConfigDetailed(String configdetailedNo)
     */
    public JpmConfigDetailed getJpmConfigDetailed(final String configdetailedNo) {
        return jpmConfigDetailedDao.getJpmConfigDetailed(new Long(configdetailedNo));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmConfigDetailedManager#saveJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed)
     */
    public void saveJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed) {
        jpmConfigDetailedDao.saveJpmConfigDetailed(jpmConfigDetailed);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmConfigDetailedManager#removeJpmConfigDetailed(String configdetailedNo)
     */
    public void removeJpmConfigDetailed(final String configdetailedNo) {
        jpmConfigDetailedDao.removeJpmConfigDetailed(new Long(configdetailedNo));
    }
    //added for getJpmConfigDetailedsByCrm
    public List getJpmConfigDetailedsByCrm(CommonRecord crm, Pager pager){
    return jpmConfigDetailedDao.getJpmConfigDetailedsByCrm(crm,pager);
    }
    
    @Override
    public void addJpmConfigDetailed(HttpServletRequest request,SysUser jsysUser)
    {
        Long price = 0l;
        String subSize = request.getParameter("jpmConfigDetailedSize");
        int size = new Integer(subSize).intValue();
        List<JpmConfigDetailed> list = new ArrayList<JpmConfigDetailed>();
        //用户变更信息后更新配置规格信息
        String complateAmount = request.getParameter("chooseConfigNum");
        String complateWeight = request.getParameter("curWeight");
        //单个商品重量
        String weight = request.getParameter("weight");
        String productTemplateNo = request.getParameter("productTemplateNo");
        String productTemplateName = request.getParameter("productTemplateName");
        
        JpmConfigSpecDetailed jpmConfigSpecDetailed = new JpmConfigSpecDetailed();
        jpmConfigSpecDetailed.setProductTemplateNo(productTemplateNo);
        jpmConfigSpecDetailed.setProductTemplateName(productTemplateName);
        if (StringUtils.isNotEmpty(complateAmount))
        {
            jpmConfigSpecDetailed.setComplateAmount(Long.parseLong(complateAmount));
        }
        if (StringUtils.isNotEmpty(complateWeight))
        {
            jpmConfigSpecDetailed.setComplateWeight(Long.parseLong(complateWeight));
        }
        
        //产品信息 
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        
        //订单信息 
        String moId = request.getParameter("moId");
        String molId = request.getParameter("molId");
        String specNo = request.getParameter("specNo");
        
        String saveType = request.getParameter("saveType");
        JpmProductWineTemplate jpmProductWineTemplate = new JpmProductWineTemplate();
        //根据模版id查询模版名称
        if(StringUtils.isNotEmpty(productTemplateNo))
        {
            jpmProductWineTemplate =  jpmProductWineTemplateDao.getJpmProductWineTemplate(Long.parseLong(productTemplateNo));
        }
        
        // 全新添加订单
        if (SAVE_CONFIG.equals(saveType))
        {
            //第一步 begin
            //新增订单配置表
            JpmMemberConfig jpmMemberConfig = new JpmMemberConfig();
            jpmMemberConfig.setCompanyCode(jsysUser.getCompanyCode());
            jpmMemberConfig.setUserCode(jsysUser.getUserCode());
            jpmMemberConfig.setMolId(Long.parseLong(molId));
            jpmMemberConfig.setMoId(Long.parseLong(moId));
            //产品信息
            JpmProductSaleTeamType jpmProductSaleTeamType = new JpmProductSaleTeamType();
            jpmProductSaleTeamType.setPttId(Long.parseLong(productId));
            jpmMemberConfig.setJpmProductSaleTeamType(jpmProductSaleTeamType);
            jpmMemberConfig.setProductName(productName);
            jpmMemberConfig.setCreatetime(new Date());
            //数量：页面所填数量
            jpmMemberConfig.setAmount(Long.parseLong(complateAmount));
            
            Map<String,Long> map = new HashMap<String,Long>();
            map.put("molId", Long.parseLong(molId));
            JpoMemberOrderList jpoMemberOrderList = jpoMemberOrderListDao.getJpoMemberOrderListByMolId(map);
            //配置总重量：用户填写数量*单个商品重量
            jpmMemberConfig.setWeight(new BigDecimal(Long.parseLong(complateAmount) * jpoMemberOrderList.getWeight().longValue()));
            
            //0表示未支付
            jpmMemberConfig.setStatus("0");
            //将订单配置数据增加
            jpmMemberConfigDao.addJpmMemberConfig(jpmMemberConfig);
            //第一步 end
            
            //第二步 begin
            // 添加配置规格表数据
            jpmConfigSpecDetailed.setCreateTime(new Date());
            jpmConfigSpecDetailed.setJpmMemberConfig(jpmMemberConfig);
            
            //获取规格下所以配件总价格
            List ls = installJpmConfigDetailed(request, size, list,jpmConfigSpecDetailed);
            jpmConfigSpecDetailed.setPrice(BigDecimal.valueOf(Long.parseLong(ls.get(0).toString())));
            jpmConfigSpecDetailed.setProductNum(BigDecimal.valueOf(Long.parseLong(ls.get(1).toString())));
            if(StringUtils.isNotEmpty(jpmProductWineTemplate.getProductTemplateName()))
            {
                jpmConfigSpecDetailed.setProductTemplateName(jpmProductWineTemplate.getProductTemplateName());
            }
            jpmConfigSpecDetailedDao.insertJpmConfigSpecDetailed(jpmConfigSpecDetailed);
            for(JpmConfigDetailed jpmConfigDetailed : list)
            {
                jpmConfigDetailed.setSpecNo(jpmConfigSpecDetailed.getSpecNo());
            }
            //第二步 end
            
            //修改订单配置状态为部分配置
            Map<String,String> modMap = new HashMap<String,String>();
            modMap.put("status", "2");
            modMap.put("moId", moId);
            jpoMemberOrderDao.modifyOrderStatusByMoId(modMap);
        }
        else if(SAVE_SPEC_CONFIG.endsWith(saveType))
        {
            String configNo = request.getParameter("configNo");
            if(StringUtils.isNotEmpty(configNo))
            {
                JpmMemberConfig jpmMemberConfig = new JpmMemberConfig();
                jpmMemberConfig.setConfigNo(Long.parseLong(configNo));
                jpmConfigSpecDetailed.setJpmMemberConfig(jpmMemberConfig);
            }
            //新增规格
            jpmConfigSpecDetailed.setCreateTime(new Date());
            //修改规格重量
            List ls = installJpmConfigDetailed(request, size, list,jpmConfigSpecDetailed);
            jpmConfigSpecDetailed.setPrice(BigDecimal.valueOf(Long.parseLong(ls.get(0).toString())));
            jpmConfigSpecDetailed.setProductNum(BigDecimal.valueOf(Long.parseLong(ls.get(1).toString())));
            //修改价格
            if(StringUtils.isNotEmpty(jpmProductWineTemplate.getProductTemplateName()))
            {
                jpmConfigSpecDetailed.setProductTemplateName(jpmProductWineTemplate.getProductTemplateName());
            }
            jpmConfigSpecDetailedDao.insertJpmConfigSpecDetailed(jpmConfigSpecDetailed);
            for(JpmConfigDetailed jpmConfigDetailed : list)
            {
                jpmConfigDetailed.setSpecNo(jpmConfigSpecDetailed.getSpecNo());
            }
        }
        else
        {
          //修改价格
            if(StringUtils.isNotEmpty(jpmProductWineTemplate.getProductTemplateName()))
            {
                jpmConfigSpecDetailed.setProductTemplateName(jpmProductWineTemplate.getProductTemplateName());
            }
            String configNo = request.getParameter("configNo");
            if(StringUtils.isNotEmpty(configNo))
            {
                JpmMemberConfig jpmMemberConfig = new JpmMemberConfig();
                jpmMemberConfig.setConfigNo(Long.parseLong(configNo));
                jpmConfigSpecDetailed.setJpmMemberConfig(jpmMemberConfig);
            }
            //修改规格下明细
            jpmConfigSpecDetailed.setSpecNo(Long.parseLong(specNo));
            List ls = installJpmConfigDetailed(request, size, list,jpmConfigSpecDetailed);
            jpmConfigSpecDetailed.setPrice(BigDecimal.valueOf(Long.parseLong(ls.get(0).toString())));
            jpmConfigSpecDetailed.setProductNum(BigDecimal.valueOf(Long.parseLong(ls.get(1).toString())));
            //变更后相应更改规格表
            jpmConfigSpecDetailed.setSpecNo(Long.parseLong(specNo));
            jpmConfigSpecDetailedDao.modifyJpmConfigSpecDetailed(jpmConfigSpecDetailed);
        }
        //三种方式都需要保存或者修改详细表数据
        //先删除再重新保存
        if(StringUtils.isNotEmpty(specNo))
        {
            jpmConfigDetailedDao.delJpmConfigDetailedBySpecNo(Long.parseLong(specNo));
        }
        else if(null != jpmConfigSpecDetailed.getSpecNo())
        {
            jpmConfigDetailedDao.delJpmConfigDetailedBySpecNo(jpmConfigSpecDetailed.getSpecNo());
        }
        
        Set<JpmConfigDetailed> configSet = new HashSet<JpmConfigDetailed>(0);
        configSet.addAll(list);
        jpmConfigDetailedDao.saveJpmConfigDetailedList(configSet);
    }

    private List<Long> installJpmConfigDetailed(HttpServletRequest request, int size,
        List<JpmConfigDetailed> list,JpmConfigSpecDetailed jpmConfigSpecDetailed)
    {
        List<Long> ls = new ArrayList<Long>();
        Long priceCount = 0l;
        Long productNum = 0l;
        for (int i = 0; i < size; i++)
        {
            JpmConfigDetailed jpmConfigDetailed = new JpmConfigDetailed();
            String configdetailedNo =
                request.getParameter("jpmConfigDetailed[" + i + "].configdetailedNo");
            String subNo = request.getParameter("jpmConfigDetailed[" + i + "].subNo");
            String subName = request.getParameter("jpmConfigDetailed[" + i + "].subName");
            String price =
                StringUtils.defaultIfEmpty(request.getParameter("jpmConfigDetailed[" + i
                    + "].price"), "0");
            String isMustSelected =
                StringUtils.defaultIfEmpty(request.getParameter("jpmConfigDetailed[" + i
                    + "].isMustSelected"), "0");
            String isMainMaterial =
                StringUtils.defaultIfEmpty(request.getParameter("jpmConfigDetailed[" + i
                    + "].isMainMaterial"), "0");
            
            String subAmount = request.getParameter("jpmConfigDetailed[" + i + "].subAmount");
            //判断是否为主料
            if("1".equals(isMainMaterial))
            {
                if(StringUtils.isNotEmpty(subAmount))
                {
                    productNum = Long.parseLong(subAmount);
                }
            }
            String specNo = request.getParameter("jpmConfigDetailed[" + i + "].specNo");
            String idf = request.getParameter("jpmConfigDetailed[" + i + "].idf");
            String isCheck = request.getParameter("jpmConfigDetailed[" + i + "].isCheck");
            String remark = request.getParameter("jpmConfigDetailed[" + i + "].remark");
//            if(StringUtils.isNotEmpty(configdetailedNo)){
//                jpmConfigDetailed.setConfigdetailedNo(Long.parseLong(configdetailedNo));
//            }
            
            jpmConfigDetailed.setSubNo(subNo);
            jpmConfigDetailed.setSubName(subName);
            jpmConfigDetailed.setSubAmount(Long.parseLong(subAmount));
            jpmConfigDetailed.setIsMustSelected(isMustSelected);
            jpmConfigDetailed.setPrice(BigDecimal.valueOf(Long.parseLong(price)));
            jpmConfigDetailed.setRemark(remark);
            jpmConfigDetailed.setIsMainMaterial(isMainMaterial);
            if(null != jpmConfigSpecDetailed)
            {
                jpmConfigDetailed.setSpecNo(jpmConfigSpecDetailed.getSpecNo());
            }
            else
            {
                specNo = request.getParameter("specNo");
                jpmConfigDetailed.setSpecNo(Long.parseLong(specNo));
            }
            if (StringUtils.isNotEmpty(idf))
            {
                if (idf.contains(","))
                {
                    idf = idf.split(",")[1];
                }
                jpmConfigDetailed.setIdf(Long.parseLong(idf));
            }
            if("0".equals(isMustSelected))
            {
                if("1".equals(isCheck))
                {
                    priceCount += Long.parseLong(price);
                    list.add(jpmConfigDetailed);
                }
            }
            else
            {
                priceCount += Long.parseLong(price);
                list.add(jpmConfigDetailed);
            }
        }
        ls.add(priceCount);
        ls.add(productNum);
        return ls;
    }
    
    @Override
    public Integer delJpmConfigDetailed(Long detailedId)
    {
        // TODO Auto-generated method stub
        return jpmConfigDetailedDao.delJpmConfigDetailed(detailedId);
    }
    
    @Override
    public Integer modifyJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed)
    {
        // TODO Auto-generated method stub
        return jpmConfigDetailedDao.modifyJpmConfigDetailed(jpmConfigDetailed);
    }
    
    @Override
    public List<JpmConfigDetailed> getJpmConfigDetailedBySpecNo(String specNo)
    {
        return jpmConfigDetailedDao.getJpmConfigDetailedBySpecNo(specNo);
    }

    @Override
    public JpmConfigDetailed getJpmConfigDetailedNumBySpecNo(String specNo)
    {
        return jpmConfigDetailedDao.getJpmConfigDetailedNumBySpecNo(specNo);
    }
}
