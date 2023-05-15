package org.jeecg.modules.demo.equipment.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.equipment.entity.EquipmentAlarm;
import org.jeecg.modules.demo.equipment.service.IEquipmentAlarmService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 设备报警表
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
@Api(tags="设备报警表")
@RestController
@RequestMapping("/equipment/equipmentAlarm")
@Slf4j
public class EquipmentAlarmController extends JeecgController<EquipmentAlarm, IEquipmentAlarmService> {
	@Autowired
	private IEquipmentAlarmService equipmentAlarmService;
	
	/**
	 * 分页列表查询
	 *
	 * @param equipmentAlarm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "设备报警表-分页列表查询")
	@ApiOperation(value="设备报警表-分页列表查询", notes="设备报警表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<EquipmentAlarm>> queryPageList(EquipmentAlarm equipmentAlarm,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EquipmentAlarm> queryWrapper = QueryGenerator.initQueryWrapper(equipmentAlarm, req.getParameterMap());
		Page<EquipmentAlarm> page = new Page<EquipmentAlarm>(pageNo, pageSize);
		IPage<EquipmentAlarm> pageList = equipmentAlarmService.page(page, queryWrapper);

		IPage<EquipmentAlarm> pageList2 = equipmentAlarmService.selectList(page , queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param equipmentAlarm
	 * @return
	 */
	@AutoLog(value = "设备报警表-添加")
	@ApiOperation(value="设备报警表-添加", notes="设备报警表-添加")
	@RequiresPermissions("equipment:equipment_alarm:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody EquipmentAlarm equipmentAlarm) {
		equipmentAlarm.setCreateTime(new Date());
		equipmentAlarmService.save(equipmentAlarm);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param equipmentAlarm
	 * @return
	 */
	@AutoLog(value = "设备报警表-编辑")
	@ApiOperation(value="设备报警表-编辑", notes="设备报警表-编辑")
	@RequiresPermissions("equipment:equipment_alarm:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody EquipmentAlarm equipmentAlarm) {
		equipmentAlarmService.updateById(equipmentAlarm);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备报警表-通过id删除")
	@ApiOperation(value="设备报警表-通过id删除", notes="设备报警表-通过id删除")
	@RequiresPermissions("equipment:equipment_alarm:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		equipmentAlarmService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备报警表-批量删除")
	@ApiOperation(value="设备报警表-批量删除", notes="设备报警表-批量删除")
	@RequiresPermissions("equipment:equipment_alarm:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.equipmentAlarmService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "设备报警表-通过id查询")
	@ApiOperation(value="设备报警表-通过id查询", notes="设备报警表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<EquipmentAlarm> queryById(@RequestParam(name="id",required=true) String id) {
		EquipmentAlarm equipmentAlarm = equipmentAlarmService.getById(id);
		if(equipmentAlarm==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(equipmentAlarm);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param equipmentAlarm
    */
//    @RequiresPermissions("equipment:equipment_alarm:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EquipmentAlarm equipmentAlarm) {
        return super.exportXls(request, equipmentAlarm, EquipmentAlarm.class, "设备报警表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("equipment:equipment_alarm:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EquipmentAlarm.class);
    }

}
