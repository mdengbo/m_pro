package com.m.business.feign.classes;

import com.m.data.entity.Classes;
import com.m.data.r.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 对外提供 classes 服务调用接口
 * @Author Mdengbo
 * @Date 2023/3/29 下午7:18
 * @Version 1.0
 */

@FeignClient(name = "m-classes") //name 服务方的wfw服务名： spring.application.name
public interface ClsFeign {

    /**
     * @description  远程调用 class 服务
     *      方法名及返回值需和  class 服务那边提供的接口一样， mapping为全路径
     * @author  Mdengbo
     * @date    2023/3/29 下午6:53
     * @param	cId	 需用 @RequestParam 标注请求数据
     * @return  com.m.data.r.R
     */
    @RequestMapping("/class/getById")
    R<Classes> getClsByCid(@RequestParam(name = "cId") Integer cId);

}
