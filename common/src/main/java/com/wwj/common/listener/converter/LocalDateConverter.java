package com.wwj.common.listener.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author wushilin
 * @version 1.0
 * @date 2021/08/31 10:29:01
 */
public class LocalDateConverter implements Converter<LocalDate> {
    @Override
    public Class supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDate convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Calendar calendar = new GregorianCalendar(1900,0,-1);
        Date gregorianDate = calendar.getTime();
        int time = cellData.getNumberValue().intValue();
        String format = DateUtils.format(org.apache.commons.lang3.time.DateUtils.addDays(gregorianDate, time), "yyyy-MM-dd");
        return LocalDate.parse(format);
    }

    @Override
    public CellData convertToExcelData(LocalDate value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData<>(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
