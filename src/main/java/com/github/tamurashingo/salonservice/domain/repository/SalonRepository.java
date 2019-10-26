package com.github.tamurashingo.salonservice.domain.repository;


import com.github.tamurashingo.salonservice.domain.model.salon.SalonModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
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
}
