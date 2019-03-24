package com.intercorp.backendchallenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.intercorp.backendchallenge.util.BigDecimalSerializer;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class InformationClients implements Serializable {

  private static final long serialVersionUID = 1478562251618011883L;

  @ApiModelProperty(required = true, value = "The average age of the clients", example = "45",
      allowEmptyValue = false)
  @JsonSerialize(using = BigDecimalSerializer.class)
  private BigDecimal averageAge;

  @ApiModelProperty(required = true, value = "The standard deviation of the clients",
      example = "17.0897", allowEmptyValue = false)
  @JsonSerialize(using = BigDecimalSerializer.class)
  private BigDecimal standardDeviationAge;

}
