package com.github.tamurashingo.salonservice.domain.model.salon;

import com.github.tamurashingo.salonservice.domain.model.UserModel;
import com.github.tamurashingo.salonservice.domain.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SalonModel implements ValueObject<SalonModel> {

    private Long salonId;
    private String salonName;
    private String description;
    private SalonStatus salonStatus;
    private UserModel user;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static enum SalonStatus {
        /// 無効
        INVALID("0"),
        /// 公開中
        OPEN("1"),
        /// 非公開
        PRIVATE("2"),
        /// 休止中
        PAUSED("3"),
        ;

        private final String status;
        private SalonStatus(final String status) {
            this.status = status;
        }
        public String getSalonStatus() {
            return this.status;
        }
    }

    public static class SalonStatusTypeHandler extends BaseTypeHandler<SalonStatus> {

        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, SalonStatus parameter, JdbcType jdbcType) throws SQLException {
            ps.setString(i, parameter.getSalonStatus());
        }

        @Override
        public SalonStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
            return typeChanger(rs.getString(columnName));
        }

        @Override
        public SalonStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            return typeChanger(rs.getString(columnIndex));
        }

        @Override
        public SalonStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            return typeChanger(cs.getString(columnIndex));
        }

        private SalonStatus typeChanger(String dbVal) {
            return java.util.Arrays.stream(SalonStatus.values())
                    .filter(v -> v.getSalonStatus().equals(dbVal))
                    .findFirst()
                    .orElse(SalonStatus.INVALID);
        }
    }


    @Override
    public boolean sameValueAs(SalonModel other) {
        return false;
    }
}
