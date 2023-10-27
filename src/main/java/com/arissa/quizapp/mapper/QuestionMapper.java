package com.arissa.quizapp.mapper;

import com.arissa.quizapp.dto.QuestionDTO;
import com.arissa.quizapp.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "id", source = "id")

    @Mapping(target = "questionTitle", source = "questionTitle")

    @Mapping(target = "option1", source = "option1")

    @Mapping(target = "option2", source = "option2")

    @Mapping(target = "option3", source = "option3")

    @Mapping(target = "option4", source = "option4")
    QuestionDTO map(Question question);

    List<QuestionDTO> mapList(List<Question> question);

}
