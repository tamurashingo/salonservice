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
package com.github.tamurashingo.salonservice.domain.repository;

import com.github.tamurashingo.salonservice.domain.model.UserModel;
import org.apache.ibatis.annotations.*;
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
    @ConstructorArgs({
            @Arg(id = true, column = "user_id", name = "userId"),
            @Arg(column = "user_email", name = "userEmail"),
            @Arg(column = "user_name", name = "userName"),
            @Arg(column = "password", name = "password"),
            @Arg(column = "user_status", name = "userStatus", typeHandler = UserModel.UserStatusTypeHandler.class),
            @Arg(column = "created_date", name = "createdDate", typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class),
            @Arg(column = "updated_date", name = "updatedDate", typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class)
    })
    public UserModel findUserByEmail(@Param("email") String email);


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
            + "   #{userStatus, typeHandler = com.github.tamurashingo.salonservice.domain.model.UserModel$UserStatusTypeHandler}, "
            + "   #{createdDate}, "
            + "   #{updatedDate} "
            + " ) "
    )
    @SelectKey(statement = "select @@identity", keyProperty = "userId", before = false, resultType = Long.class)
    public long register(UserModel user);

    @Update(
              " update "
            + "   user "
            + " set "
            + "   user_email = #{userEmail}, "
            + "   user_name = #{userName}, "
            + "   password = #{password}, "
            + "   user_status = #{userStatus, typeHandler = com.github.tamurashingo.salonservice.domain.model.UserModel$UserStatusTypeHandler}, "
            + "   updated_date = #{updatedDate} "
            + " where user_id = #{userId}"
    )
    public long save(UserModel user);
}
