package com.github.tamurashingo.salonservice.domain.repository;


import com.github.tamurashingo.salonservice.domain.model.salon.SalonModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
            + "   #{salonStatus, typeHandler = com.github.tamurashingo.salonservice.domain.model.salon.SalonModel$SalonStatusTypeHandler}, "
            + "   #{user.userId}, "
            + "   #{createdDate}, "
            + "   #{updatedDate} "
            + " ) "
    )
    @SelectKey(statement = "select @@identity", keyProperty = "salonId", before = false, resultType = Long.class)
    long register(SalonModel salon);


    @Select(
              " select "
            + "   salon.salon_id, "
            + "   salon.salon_name, "
            + "   salon.description, "
            + "   salon.created_by, "
            + "   salon.created_date, "
            + "   salon.updated_date, "
            + "   user.user_id, "
            + "   user.user_email, "
            + "   user.user_name, "
            + "   user.password, "
            + "   user.user_status, "
            + "   user.created_date, "
            + "   user.update_date, "
            + " from "
            + "   salon, "
            + "   user "
            + " where "
            + "   salon.salon_id = #{salon_id}"
            + " and "
            + "   salon.user_id = user.user_id "
    )
    SalonModel searchById(@Param("salon_id") Long salonId);

}
