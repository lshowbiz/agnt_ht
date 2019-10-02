package com.joymain.jecs.util.json;

import java.util.Map;

import net.sf.json.JSONObject;

public class InterfaceJsonUtil {
	
	/**
	 * Json字符串转换为对象
	 * @param <T>：泛型
	 * @param json：json格式内容
	 * @param className：泛型类名称
	 * @param classMap：返回的<T>对象中List集合子对象对应的Map集合
	 *        classMap：如果返回的json对象只有一层，没有集合List关系，则传递null值即可；
	 *                  如果父子两层或多层级别的关系，则对于所有的List<泛型>，都要统一加入到classMap中
	 *        具体实例如下！
	 * @return：返回<T>对象
	 * ***
	 */
	@SuppressWarnings("unchecked")
	public static <T> T returnnoJsonToModel(String json,Class<T> className,Map<String,Class> classMap)throws Exception{
		T t = null;
		
		t = className.newInstance();
		JSONObject jsonStu = JSONObject.fromObject(json);
		/* 在JSONObject.toBean的时候如果转换的类中有集合,可以先定义
         * Map<String, Class> classMap = new HashMap<String, Class>();
         * 在classMap中put你要转换的类中的集合名,像:classMap.put("teachers", Teacher.class);
         * 然后在toBean()的时候把参数加上, */
		if(classMap!=null){
			t = (T)JSONObject.toBean(jsonStu, className, classMap);
		}else{
			t = (T) JSONObject.toBean(jsonStu, className);  
		}
		
		return t;
	}
	
	/**
	 * Json字符串转换为对象
	 * @param <T>：泛型
	 * @param json：json格式内容
	 * @param className：泛型类名称
	 * @param classMap：返回的<T>对象中List集合子对象对应的Map集合
	 *        classMap：如果返回的json对象只有一层，没有集合List关系，则传递null值即可；
	 *                  如果父子两层或多层级别的关系，则对于所有的List<泛型>，都要统一加入到classMap中
	 *        具体实例如下！
	 * @return：返回<T>对象
	 * ***
	 */
	@SuppressWarnings("unchecked")
	public static <T> T returnnoJsonToSysParameter(String json,Class<T> className,Map<String,Class> classMap)throws Exception{
		T t = null;
		
		t = className.newInstance();
		JSONObject jsonStu = JSONObject.fromObject(json);
		((SysParameter)t).setTimestamp(jsonStu.getString("timestamp"));
		((SysParameter)t).setContent(jsonStu.getString("content"));
		((SysParameter)t).setFlag(jsonStu.getString("flag"));
		((SysParameter)t).setCharset(jsonStu.getString("charset"));
		((SysParameter)t).setVer(jsonStu.getString("ver"));
		((SysParameter)t).setMethod(jsonStu.getString("method"));
		((SysParameter)t).setType(jsonStu.getString("type"));
		((SysParameter)t).setSign(jsonStu.getString("sign"));
		
		
		return t;
	}
	
	public static void main(String[] args) throws Exception {
		/*PdLogisticsBase pdLogisticsBaseT = null;
		String str = "{\"MEMBER_ORDER_NO\":\"M020141202000013\",\"SI_NO\":\"LO012014120200005\",\"wms_do\":\"DO20949879022\",\"status\":\"1\",\"operator\":\"仓库管理员\",\"status_time\":\"2014-11-25 16:40:43\",\"status_code\":\"0006\",\"status_name\":\"已发货\",\"mail_list\":[{\"mail_no\":\"1201420920980\",\"name\":\"韵达\",\"status\":\"0000\",\"pdLogisticsBaseDetail_items\":[{\"erp_goods_bn\":\"P09080100101CN0\",\"PRODUCT_NO\":\"P09080100101CN0\",\"qty\":1}]}]}";
		 Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
        classMap.put("mail_list", PdLogisticsBaseNum.class);
        classMap.put("pdLogisticsBaseDetail_items", PdLogisticsBaseDetail.class);
        
		//将json字符串转换成java对象
		pdLogisticsBaseT = InterfaceJsonUtil.returnnoJsonToModel(str,PdLogisticsBase.class,classMap);
		System.out.println("pdLogisticsBaseT:"+pdLogisticsBaseT);*/
	}
	
	/**
	  * 使用实例，如下有三层：
	  *    第一层：退单数据Returno
	  *    第二层：退单明细Returno_items
	  *    第三层：退单明细子明细Returno_items_child
        String json = "{\"order_bn\":\"201410230491\",\"eo_bo\":\"EX201410230491\",\"returno_items\":
                           [{\"goods_bn\":\"BN00101\",\"name\":\"商品名称\",\"erp_goods_bn\":\"ERP商品编码\",\"nums\":10,\"price\":10000,\"returno_items_child\":
                               [{\"child_goods_bn\":\"ZI001\",\"child_name\":\"子编码001\"},
                                {\"child_goods_bn\":\"ZI002\",\"child_name\":\"子编码002\"}]},
                           {\"goods_bn\":\"BN00102\",\"name\":\"商品名称2\",\"erp_goods_bn\":\"ERP商品编码\",\"nums\":101,\"price\":100001,\"returno_items_child\":
                               [{\"child_goods_bn\":\"ZI003\",\"child_name\":\"子编码003\"},
                                {\"child_goods_bn\":\"ZI004\",\"child_name\":\"子编码004\"}
                          ]}
                       ]}";
        
        //Json字符串转JSONObject对象
        JSONObject jsonStu = JSONObject.fromObject(json);
        
        //将Model对象中有List属性的子类都进行强制类型转换，放到Map中
        //注释：如果<T>对象中没有子对象的List集合，则classMap传递null即可
        Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
        classMap.put("returno_items", Returno_items.class);//将子对象集合List加入到map中
        classMap.put("returno_items_child", Returno_items_child.class);//将子对象集合List加入到map中
       
        //返回对象
        Returno stu2=(Returno) JSONObject.toBean(jsonStu, Returno.class, classMap);
	 **/
}
