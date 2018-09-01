package com.wang.face.mapper;

import com.wang.face.pojo.FaceTable;
import com.wang.face.pojo.FaceTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceTableMapper {
    int countByExample(FaceTableExample example);

    int deleteByExample(FaceTableExample example);

    int insert(FaceTable record);

    int insertSelective(FaceTable record);

    List<FaceTable> selectByExample(FaceTableExample example);

    int updateByExampleSelective(@Param("record") FaceTable record, @Param("example") FaceTableExample example);

    int updateByExample(@Param("record") FaceTable record, @Param("example") FaceTableExample example);
}