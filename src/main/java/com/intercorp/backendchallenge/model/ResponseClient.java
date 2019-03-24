package com.intercorp.backendchallenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(value = Include.NON_NULL)
@ApiModel(description = "Response in error cases")
public class ResponseClient implements Serializable {

  private static final long serialVersionUID = -392349135058480377L;

  @ApiModelProperty(required = true, value = "The error code", example = "E001",
      allowEmptyValue = false)
  private String errorCode;

  @ApiModelProperty(required = true, value = "The description of the error",
      example = "Error saving the client information", allowEmptyValue = false)
  private String description;

}
