package com.github.tamurashingo.salonservice.domain.model.salon;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalonRegisterModel {

    /** サロン名 */
    private String salonName;
    /** 詳細 */
    private String description;
    /** ユーザID */
    private Long userId;
}
