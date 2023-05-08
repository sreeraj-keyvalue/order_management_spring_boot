package com.ecommerce.order.util;

import com.ecommerce.order.dto.PageData;
import com.ecommerce.order.dto.ResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseTransformAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {

    String className = returnType.getContainingClass().toString();

    return (className.contains("Controller"));
  }

  @Override
  @Nullable
  @SuppressWarnings("unchecked")
  public ResponseDto beforeBodyWrite(@Nullable Object body, MethodParameter returnType,
      MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {

    if (body instanceof Page) {
      Page<Object> pageBody = (Page<Object>) body;
      PageData data = new PageData(pageBody.getContent(), pageBody.isLast(), pageBody.getNumber(),
          pageBody.getSize(), pageBody.getTotalPages(), pageBody.getTotalElements());

      return new ResponseDto(data, null, null);
    }
    return new ResponseDto(body, null, null);

  }

}
