package com.github.tamurashingo.salonservice.domain.repository;


import com.github.tamurashingo.salonservice.domain.model.salon.SalonModel;
import com.github.tamurashingo.salonservice.domain.model.salon.SalonRegisterModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Mapper
public interface SalonRepository {

    @Insert(
              " insert into "
            + "   salon "
            + " ( "
            + "    salon_name, "
            + "    description, "
            + "    salon_status, "
            + "    created_by, "
            + "    created_date, "
            + "    updated_date "
            + " ) "
            + " values ( "
            + "   #{salonName}, "
            + "   #{description}, "
            + "   #{com.github.tamurashingo.salonservice.domain.model.salon.SalonModel$SalonStatus.INVALID, typeHandler = com.github.tamurashingo.salonservice.domain.model.salon.SalonModel$SalonStatusTypeHandler}, "
            + "   #{java.time.LocalDateTime.now()}, "
            + "   #{java.time.LocalDateTime.now()} "
            + " } "
    )
    long register(SalonRegisterModel salon);
}
