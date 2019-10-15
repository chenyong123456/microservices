package cn.knowimage.pathwayform.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StagePojo {
    private String primary_diagnosi;
    private String long_term_orders;
    private String temporary_orders;
    private String discharge_orders;
    private String notification;
    private String major_nursing_work;
    private String disease_variation_status;
    private String reason;
    private String nurse_onduty;
    private String day_shiftName;
    private String night_shiftSName;
    private String night_shiftBName;
    private String time_undefinedName;
    private String physician_name;
}
