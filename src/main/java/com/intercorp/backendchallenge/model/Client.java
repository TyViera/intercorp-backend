package com.intercorp.backendchallenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDate;
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
@ApiModel(description = "Client information that keep in memory database")
public class Client implements Serializable {

  private static final long serialVersionUID = 1404482074769624700L;

  @ApiModelProperty(required = true, value = "The name of the client", example = "Nazaret",
      allowEmptyValue = false)
  private String name;

  @ApiModelProperty(required = true, value = "The last name of the client", example = "Viera",
      allowEmptyValue = false)
  private String lastName;

  @ApiModelProperty(required = true, value = "The age of the client", example = "23",
      allowEmptyValue = false)
  private Integer age;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
  @ApiModelProperty(required = true, value = "The born date of the client in ISO 8601",
      example = "1995-08-12", allowEmptyValue = false)
  private LocalDate bornDate;

}
