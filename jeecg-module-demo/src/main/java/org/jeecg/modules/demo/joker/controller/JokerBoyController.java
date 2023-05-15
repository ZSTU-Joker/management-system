package org.jeecg.modules.demo.joker.controller;

import java.util.Arrays;
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
import org.jeecg.modules.demo.joker.entity.JokerBoy;
import org.jeecg.modules.demo.joker.service.IJokerBoyService;

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
 * @Description: 男孩
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
@Api(tags="男孩")
@RestController
@RequestMapping("/joker/jokerBoy")
@Slf4j
public class JokerBoyController extends JeecgController<JokerBoy, IJokerBoyService> {
	@Autowired
	private IJokerBoyService jokerBoyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param jokerBoy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "男孩-分页列表查询")
	@ApiOperation(value="男孩-分页列表查询", notes="男孩-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<JokerBoy>> queryPageList(JokerBoy jokerBoy,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<JokerBoy> queryWrapper = QueryGenerator.initQueryWrapper(jokerBoy, req.getParameterMap());
		Page<JokerBoy> page = new Page<JokerBoy>(pageNo, pageSize);
		IPage<JokerBoy> pageList = jokerBoyService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param jokerBoy
	 * @return
	 */
	@AutoLog(value = "男孩-添加")
	@ApiOperation(value="男孩-添加", notes="男孩-添加")
	@RequiresPermissions("joker:joker_boy:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody JokerBoy jokerBoy) {
		jokerBoyService.save(jokerBoy);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param jokerBoy
	 * @return
	 */
	@AutoLog(value = "男孩-编辑")
	@ApiOperation(value="男孩-编辑", notes="男孩-编辑")
	@RequiresPermissions("joker:joker_boy:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody JokerBoy jokerBoy) {
		jokerBoyService.updateById(jokerBoy);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "男孩-通过id删除")
	@ApiOperation(value="男孩-通过id删除", notes="男孩-通过id删除")
	@RequiresPermissions("joker:joker_boy:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		jokerBoyService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "男孩-批量删除")
	@ApiOperation(value="男孩-批量删除", notes="男孩-批量删除")
	@RequiresPermissions("joker:joker_boy:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jokerBoyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "男孩-通过id查询")
	@ApiOperation(value="男孩-通过id查询", notes="男孩-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<JokerBoy> queryById(@RequestParam(name="id",required=true) String id) {
		JokerBoy jokerBoy = jokerBoyService.getById(id);
		if(jokerBoy==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(jokerBoy);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param jokerBoy
    */
    @RequiresPermissions("joker:joker_boy:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JokerBoy jokerBoy) {
        return super.exportXls(request, jokerBoy, JokerBoy.class, "男孩");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("joker:joker_boy:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, JokerBoy.class);
    }

}
