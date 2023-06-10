package co.istad.photostad.api.requesttutorials;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface RequestTutorialMapper {
    @SelectProvider(type = RequestTutorialProvider.class, method = "buildFindRequestTutorialByIdSql")
    @Result(column = "user_id", property = "userId")
    Optional<RequestTutorial> findById(@Param("id") Integer id);

    @InsertProvider(type = RequestTutorialProvider.class, method = "buildInsertRequestTutorialSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean insert(@Param("r") RequestTutorial requestTutorial);

    @SelectProvider(type = RequestTutorialProvider.class, method = "buildSelectAllRequestTutorialSql")
    @Result(column = "user_id", property = "userId")
    List<RequestTutorial> findAll();

    @Select("SELECT EXISTS(SELECT *FROM request_tutorials WHERE id=#{id})")
    boolean isRequestTutorialExits(@RequestParam("id") Integer id);

    @UpdateProvider(type = RequestTutorialProvider.class, method = "buildUpdateRequestTutorialByIdSql")
    boolean update(@Param("r") RequestTutorial requestTutorial);
}
