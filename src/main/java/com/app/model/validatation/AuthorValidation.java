package com.app.model.validatation;

import com.app.jsonmodel.AuthorCreateJsonModel;
import com.app.model.returnResult.JsonResult;

public class AuthorValidation {
    public static JsonResult validateCreate (AuthorCreateJsonModel model){
        if(model.getName().isEmpty() || model.getName() == null)
            return new JsonResult(false, "name is required");
        return new JsonResult(true, "Validate Okay");
    }
}
