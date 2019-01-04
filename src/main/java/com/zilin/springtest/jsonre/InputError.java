package com.zilin.springtest.jsonre;

import com.zilin.springtest.entity.ErrorMsg;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class InputError extends JsonStr {
    private ErrorMsg errorMsg;
}
