package com.app.action;

import com.app.context.InitialContext;
import com.app.model.returnResult.DatabaseQueryResult;
import com.opensymphony.xwork2.ActionSupport;

public class DashBoardAction extends ActionSupport {
    InitialContext initialContext;
    public String get(){
        return "success";
    }
    public String init(){
        initialContext = new InitialContext();
        DatabaseQueryResult DQR = initialContext.initDB();
        if(DQR.isSuccess()){
            return "success";
        }else{
            return "bad";
        }
    }
}
