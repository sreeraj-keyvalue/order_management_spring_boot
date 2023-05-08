package com.ecommerce.order.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageData {

  private List<Object> content;

  private Boolean last;

  private Integer pageNumber;

  private Integer pageSize;

  private Integer totalPages;

  private Long totalElements;

}
