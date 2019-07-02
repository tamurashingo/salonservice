/*-
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 tamura shingo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.DateTypeHandler;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {

    @Select(
              " select "
            + "   user_id, "
            + "   user_email, "
            + "   user_name, "
            + "   password, "
            + "   user_status, "
            + "   created_date, "
            + "   updated_date "
            + " from "
            + "   user "
            + " where "
            + "   user_email = #{email} "
    )
    @Results( id = "User", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_email", property = "userEmail"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "password", property = "password"),
            @Result(column = "user_status", property = "userStatus", typeHandler = User.UserStatusTypeHandler.class),
            @Result(column = "created_date", property = "createdDate", typeHandler = DateTypeHandler.class),
            @Result(column = "updated_date", property = "updatedDate", typeHandler = DateTypeHandler.class)
    })
    public User findUserByEmail(@Param("email") String email);


    @Insert(
              " insert into "
            + "   user "
            + " ( "
            + "   user_email, "
            + "   user_name, "
            + "   password, "
            + "   user_status, "
            + "   created_date, "
            + "   updated_date "
            + " ) "
            + " values ( "
            + "   #{userEmail}, "
            + "   #{userName}, "
            + "   #{password}, "
            + "   #{userStatus, typeHandler = com.github.tamurashingo.salonservice.domain.model.User$UserStatusTypeHandler}, "
            + "   #{createdDate}, "
            + "   #{updatedDate} "
            + " ) "
    )
    public long register(User user);

    @Update(
              " update "
            + "   user "
            + " set "
            + "   user_email = #{userEmail}, "
            + "   user_name = #{userName}, "
            + "   password = #{password}, "
            + "   user_status = #{user_status}," // typeHandler = com.github.tamurashingo.salonservice.domain.model.User.UserStatusTypeHandler}, "
            + "   updated_date = #{updatedDate} "
    )
    public long save(User user);
}
