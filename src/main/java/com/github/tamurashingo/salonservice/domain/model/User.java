package com.github.tamurashingo.salonservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
public class User implements java.io.Serializable {

    private Long userId;
    private String userEmail;
    private String userName;
    private String password;
    private UserStatus userStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public boolean isEnabled() {
        return this.userStatus == UserStatus.REGISTERED;
    }

    public User(String userEmail, String userName, String password, UserStatus userStatus, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.userId = 0L;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        this.userStatus = userStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }


    /**
     * ユーザステータス
     */
    public static enum UserStatus {
        /// 無効
        INVALID("0"),
        /// 登録済み
        REGISTERED("1"),
        /// 仮登録中
        TEMPORARY("2"),
        ;

        private final String status;
        private UserStatus(final String status) {
            this.status = status;
        }
        public String getUserStatus() {
            return this.status;
        }
    }

    public static class UserStatusTypeHandler extends BaseTypeHandler<UserStatus> {

        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, UserStatus parameter, JdbcType jdbcType) throws SQLException {
            ps.setString(i, parameter.getUserStatus());
        }

        @Override
        public UserStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
            return typeChanger(rs.getString(columnName));
        }

        @Override
        public UserStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            return typeChanger(rs.getString(columnIndex));
        }

        @Override
        public UserStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            return typeChanger(cs.getString(columnIndex));
        }

        private UserStatus typeChanger(String dbVal) {
            return java.util.Arrays.stream(UserStatus.values())
                    .filter(v -> v.getUserStatus().equals(dbVal))
                    .findFirst()
                    .orElse(UserStatus.INVALID);
        }
    }
}
