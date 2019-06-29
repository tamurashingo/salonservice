package com.github.tamurashingo.salonservice.domain.model;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class User implements java.io.Serializable, UserDetails {

    private Long userId;
    private String userEmail;
    private String userName;
    private String password;
    private UserStatus userStatus;
    private Date createdDate;
    private Date updatedDate;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.userStatus == UserStatus.REGISTERED;
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

    public class UserStatusHandler extends BaseTypeHandler<UserStatus> {

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
