package org.jeecg.modules.demo.equipment.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.equipment.entity.EquipmentAlarm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 设备报警表
 * @Author: jeecg-boot
 * @Date: 2023-03-28
 * @Version: V1.0
 */
public interface IEquipmentAlarmService extends IService<EquipmentAlarm> {

    IPage<EquipmentAlarm> selectList(Page<EquipmentAlarm> page, QueryWrapper<EquipmentAlarm> queryWrapper);
}
