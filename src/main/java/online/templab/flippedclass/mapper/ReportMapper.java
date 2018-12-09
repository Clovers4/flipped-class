package online.templab.flippedclass.mapper;

import online.templab.flippedclass.entity.Report;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface ReportMapper extends Mapper<Report> {
}