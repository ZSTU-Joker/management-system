package org.jeecg.modules.demo.equipment.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 设备报警表
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
@Data
@TableName("equipment_alarm")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="equipment_alarm对象", description="设备报警表")
public class EquipmentAlarm implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**0表示未删除1表示删除*/
	@Excel(name = "0表示未删除1表示删除", width = 15)
    @ApiModelProperty(value = "0表示未删除1表示删除")
    @TableLogic
    private java.lang.Integer delFlag;
	/**设备类型*/
	@Excel(name = "设备类型", width = 15)
    @ApiModelProperty(value = "设备类型")
    private java.lang.String deviceType;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
    private java.lang.String deviceName;
	/**报警开始时间*/
	@Excel(name = "报警开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报警开始时间")
    private java.util.Date startDatetime;
	/**报警名称*/
	@Excel(name = "报警名称", width = 15)
    @ApiModelProperty(value = "报警名称")
    private java.lang.String alarmInfo;
	/**报警等级*/
	@Excel(name = "报警等级", width = 15)
    @ApiModelProperty(value = "报警等级")
    private java.lang.String alarmType;
	/**报警*/
	@Excel(name = "报警", width = 15)
    @ApiModelProperty(value = "报警")
    private java.lang.String alarmReason;
	/**解决方法*/
	@Excel(name = "解决方法", width = 15)
    @ApiModelProperty(value = "解决方法")
    private java.lang.String solvingMethod;
	/**结束时间*/
	@Excel(name = "结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private java.util.Date endingDatetime;
}
