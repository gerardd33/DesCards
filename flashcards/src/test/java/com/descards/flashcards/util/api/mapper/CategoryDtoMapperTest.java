package com.descards.flashcards.util.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.descards.flashcards.api.dto.CategoryDto;
import com.descards.flashcards.model.entity.Category;
import com.descards.flashcards.resource.TestResource;
import org.junit.jupiter.api.Test;

class CategoryDtoMapperTest {

  private static final TestResource TEST_RESOURCE = new TestResource();

  @Test
  public void testMapToDto() {
    // given
    Category category = TEST_RESOURCE.getCategory();

    // when
    CategoryDto categoryDto = CategoryDtoMapper.mapToDto(category);

    //then
    assertEquals(category.getId(), categoryDto.getId());
    assertEquals(category.getName(), categoryDto.getName());
    assertEquals(category.getSpecialFields(), categoryDto.getSpecialFields());
  }
}